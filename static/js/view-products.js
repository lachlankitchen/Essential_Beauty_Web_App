var productsApi = '/api/products/';
var categoriesApi = '/api/categories/';
var categoriesFilterApi = ({category}) => `/api/categories/${category}`;

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array(),
            categories: new Array()
        };
    },

    mounted() {
        // semicolon separated statements

        // products are loaded when the page is loaded:
        this.getProducts();
        this.getCategories();

        // alert("Mounted: view-products.js");

    },

    methods: {
        // comma separated function declarations

        getStudents() {

            axios.get(productsApi)
                    .then(response => {
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

        getCategories() {
            axios.get(categoriesApi)
                    .then(response => {
                        this.categories = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

        filterByCategory(category) {
            axios.get(categoriesFilterApi({'category': category}))
                    .then(response => {
                        this.products = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },

        buyProduct(product) {
            // store the product in the data store
            dataStore.commit("selectProduct", product);
            // redirects the browser to the “how many do you want to buy” page
            window.location = "buy-product.html";
        }
    },

    // other modules
    mixins:[NumberFormatter]

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);

import { NumberFormatter } from './number-formatter.js';

// mount the page - this needs to be the last line in the file
app.mount("main");