
const getProducts = async () =>{
    const response = await fetch("/api/products");
    const movies = await response.json();
    return products;
}