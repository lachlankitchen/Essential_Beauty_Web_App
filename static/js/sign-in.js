var customersApi = '/api/customers/{username}';
var getByUsernameApi = ({username}) => `/api/customers/${username}`;


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
            this.createToken(this.customer.username, this.customer.password);

            axios.get(getByUsernameApi({'username': this.customer.username}))
                    .then(response => {
                        this.customer = response.data;
                        dataStore.commit("signIn", this.customer);
                        window.location = "view-products.html";
                    })
                    .catch(error => {
                        console.error(error);
                        alert("signIn: Invalid credentials, could not find customer with supplied username and password - check the console for details.");
                    });
        }
    },

    // other modules
    mixins: [BasicAccessAuthentication]

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
// import data store
import { dataStore } from './data-store.js'

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

app.use(dataStore);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");