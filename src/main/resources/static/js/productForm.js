const productsControl = new ProductsController();
//let storeImage = ""

//When user clicks on 'Save Item', calls API to add items to the database
//Add an 'onsubmit' event listener for productform to add a product
newItemForm.addEventListener('submit', (event) => {
    //Prevent default action, do not need the form to submit first (1) Form Validation
    //2) we are using our own fetch method to send the data over to the backend (REST API)
    event.preventDefault();

    // Select the inputs
    const newItemTitleInput = document.querySelector('#newItemTitleInput');
    const newItemDescription = document.querySelector('#newItemDescription');
    const newItemDate = document.querySelector('#newItemDate');

    // Get the values of the inputs - variable names to be same as MySQL columns
    const title = newItemTitleInput.value;
    const description = newItemDescription.value;
    const targetDate = newItemDate.value;

    // Clear the form
    newItemTitleInput.value = '';
    newItemDescription.value = '';
    newItemDate.value = '';

    // Add the task to the task manager
    //after getting all values and object from the form, we will call the addItem method in the ProductsController class to perform the POST HTTP request by calling the REST API provided by the backend
    productsControl.addItem(title, description, targetDate);

});

// select file input
//const input = document.querySelector('#newItemImageFile');

// add event listener
//input.addEventListener('change', () => {
//    storeImage = input.files[0];
    //storing the first file element to the variable
    //file[0] object can be image, audio, video, pdf, word doc
//});
