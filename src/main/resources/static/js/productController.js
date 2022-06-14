
const createHTMLList = (index, title, description, targetDate) =>
`
    <table>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Target Date</th>
    </tr>
    <tr>
        <td>${title}</td>
        <td>${description}</td>
        <td>${targetDate}</td>
    </tr>
    </table>
`;

class ProductsController
{
    constructor()
    {
        //Configuration of dev and prod URL - usually fetch a JSON file at API in the dev or prod environment
     /*   this.domainURL_Dev = "http://localhost:8090/";
        this.domainURL_Prod = "https://deswebproject.herokuapp.com/";

        this.addItemAPI = this.domainURL_Prod + "item/add";
        this.allItemAPI = this.domainURL_Prod + "item/all";*/

        this._items = [];       //create an array to store the details of product items
    }

    //method to add the items into the database
    addItem(title, description, targetDate)
        {
            var productController = this;
            const formData = new FormData();
            formData.append('title', title);
            formData.append('description', description);
            formData.append('targetDate',targetDate);

           fetch('http://localhost:8090/item/add', {
                 method: 'POST',
                 body: formData
                 })
                 .then(function(response) {
                     console.log(response.status); // Will show you the status
                     if (response.ok) {
                         alert("Successfully Added Product!")
                     }
                      else {
                         throw Error(response.statusText);
                     }
                 })
                 .catch((error) => {
                     console.error('Error:', error);
                     alert("Error adding item to Product")
                 });
        }

    //This method will do the fetch method to fetch data from database using the REST API endpoint from Spring Boot
    displayItem()
    {
        let productController = this;
        productController._items = [];

        fetch('http://127.0.0.1:8090/item/all')
                    .then((resp) => resp.json())    //default is get HTTP method
                    .then(function(data) {
                        console.log("2. receive data")
                        console.log(data);
                        data.forEach(function (item, index) {

                            const itemObj = {
                                id: item.id,
                                title: item.title,
                                description: item.description,
                                targetDate: item.targetDate
                           };
                            productController._items.push(itemObj);
                      });

                      productController.renderProductPage();

                    })
                    .catch(function(error) {
                        console.log(error);
                    });
    }

    //based on the item fetched from the displayItem() method and show the products product page
    renderProductPage()
    {
        let productHTMLList = [];

        for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];            //assign the individual item to the variable

            const productHTML = createHTMLList(i, item.title, item.description, item.targetDate);

            productHTMLList.push(productHTML);
        }

        //Join all the elements/items in my productHTMLList array into one string, and seperate by a break
        const pHTML = productHTMLList.join('\n');
        document.querySelector('#row').innerHTML = pHTML;


        //addEventListener - click
       /*for (let i=0; i<this._items.length; i++)
        {
            const item = this._items[i];
            document.getElementById(i).addEventListener("click", function() { displayProductDetails(item);} );
        }*/
    }
}   //End of ProductsController class
