import { useState } from 'react'
import CategoryList from './components/CategoryList'
import CategoryForm from './components/CategoryForm'
import './App.css'
import ProductForm from './components/ProductForm';

function App() {
  const [refresh, setRefresh] = useState(false);

  return (
    <>
    <h1>🛒 Product Catalog</h1>
    <CategoryForm onCategoryAdded={() => setRefresh(!refresh)}/>
      <CategoryList key={refresh} />
    <ProductForm />
    </>
  )
}

export default App
