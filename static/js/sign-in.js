var customersApi = '/api/customers/{username}';

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            customer: new Object()
        };
    },

    mounted() {
        // semicolon separated statements

        // alert("Mounted: sign-in.js");

    },

    methods: {
        // comma separated function declarations

        signIn(customer) {

            axios.get(customersApi)
                    .then(response => {
                        this.customers = response.data;
                        dataStore.commit("signIn", this.customer);
                        window.location = "view-products.html";
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

    },

    // other modules
    mixins: []

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
// import data store
import { dataStore } from './data-store.js'

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

app.use(dataStore);

// mount the page - this needs to be the last line in the file
app.mount("main");