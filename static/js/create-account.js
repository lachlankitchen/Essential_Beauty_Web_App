var registerApi = '/api/register/';


const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            customer: new Object()
        };
    },


    mounted() {
        // semicolon separated statements
        
        // alert("Mounted: create-account.js");
    },

    methods: {
        // comma separated function declarations
        createAccount() {
            axios.post(registerApi, this.customer)
                    .then(() => {
                        window.location = 'sign-in.html';
                    })
                    .catch(error => {
                        alert(error.response.data.message);
                    });
        }
    },

    // other modules
    mixins: []

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main"); 