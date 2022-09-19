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
        }
    },

    // other modules
    mixins: []

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
import { sessionStore } from './session-store.js';

app.use(sessionStore);

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");