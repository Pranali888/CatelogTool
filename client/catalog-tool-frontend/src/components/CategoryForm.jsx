import React, { useState } from "react";

function CategoryForm() {
  const [formData, setFormData] = useState({
    name: "",
    slug: "",
    description: ""
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/categories", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        setMessage("Category created successfully ✅");
        setFormData({ name: "", slug: "", description: "" });
      } else {
        setMessage("❌ Failed to create category");
      }
    } catch (err) {
      console.error(err);
      setMessage("⚠️ Error: " + err.message);
    }
  };

  return (
    <div className="form-container">
      <h2>Create Category</h2>
      <form onSubmit={handleSubmit}>
        <label>Name</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />

        <label>Slug</label>
        <input
          type="text"
          name="slug"
          value={formData.slug}
          onChange={handleChange}
          required
        />

        <label>Description</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
        ></textarea>

        <button type="submit">Create</button>
      </form>

      {message && <p className="msg">{message}</p>}
    </div>
  );
}

export default CategoryForm;
