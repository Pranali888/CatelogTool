import React, { useState, useEffect } from "react";
import axios from "axios";

function ProductForm() {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [categoryId, setCategoryId] = useState("");
  const [categories, setCategories] = useState([]);

  // Fetch categories to show in dropdown
  useEffect(() => {
    axios.get("http://localhost:8080/api/categories")
      .then((res) => setCategories(res.data))
      .catch((err) => console.error("Error fetching categories:", err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const product = { name, description, price, categoryId };
    try {
      await axios.post("http://localhost:8080/api/products", product);
      alert("✅ Product created successfully!");
      setName("");
      setDescription("");
      setPrice("");
      setCategoryId("");
    } catch (error) {
      console.error("Error creating product:", error);
      alert("❌ Failed to create product");
    }
  };

  return (
    <div>
      <h2>Create Product</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Product Name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Description:</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>

        <div>
          <label>Price:</label>
          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Category:</label>
          <select
            value={categoryId}
            onChange={(e) => setCategoryId(e.target.value)}
            required
          >
            <option value="">-- Select Category --</option>
            {categories.map((cat) => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </div>

        <button type="submit">Save Product</button>
      </form>
    </div>
  );
}

export default ProductForm;