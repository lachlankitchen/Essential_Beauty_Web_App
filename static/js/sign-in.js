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

        signIn() {

            axios.get(customersApi)
                    .then(response => {
                        this.customers = response.data;
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
import { sessionStore } from './session-store.js';

app.use(sessionStore);

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);


// mount the page - this needs to be the last line in the file
app.mount("main");