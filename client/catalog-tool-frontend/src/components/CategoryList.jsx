import React, { useEffect, useState } from "react";
import axios from "axios";

function CategoryList() {
  const [categories, setCategories] = useState([]);

//   useEffect(() => {
//     axios
//       .get("http://localhost:8080/api/categories")
//       .then((res) => {
//         setCategories(res.data);
//       })
//       .catch((err) => {
//         console.error("Error fetching categories:", err);
//       });
//   }, []);

useEffect(() => {
    axios.get("http://localhost:8080/api/categories")
      .then((res) => setCategories(res.data))
      .catch((err) => console.error("Error fetching categories:", err));
  }, []);

useEffect(() => {
  fetch("http://localhost:8080/api/categories")
    .then((res) => res.json())
    .then((data) => {
      console.log("API Response:", data); // Debug
      // If API returns object with a wrapper (like {content: [...]})
      if (Array.isArray(data)) {
        setCategories(data);
      } else if (Array.isArray(data.content)) {
        setCategories(data.content);
      } else {
        setCategories([]); // fallback
      }
    })
    .catch((err) => console.error("Error fetching categories:", err));
}, []);

  return (
    <div>
      <h2>Categories</h2>
      <ul>
       {categories.map((cat) => (
          <li key={cat.id}>{cat.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default CategoryList;