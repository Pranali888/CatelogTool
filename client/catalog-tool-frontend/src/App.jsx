import { useState } from 'react'
import CategoryList from './components/CategoryList'
import CategoryForm from './components/CategoryForm'
import './App.css'

function App() {
  const [refresh, setRefresh] = useState(false);

  return (
    <>
    <h1>🛒 Product Catalog</h1>
    <CategoryForm onCategoryAdded={() => setRefresh(!refresh)}/>
      <CategoryList key={refresh} />
    </>
  )
}

export default App
