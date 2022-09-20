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
            const customer = {customerId: this.customerId, username: this.username, firstName: this.firstName, surname: this.surname, password: this.password, shippingAddress: this.shippingAddress, emailAddress: this.emailAddress};
            
            axios.post(registerApi, this.customer)
                    .then(() => {
                        window.location = 'sign-in.html';
                    })
                    .catch(error => {
                        alert("createAccount: " + error.response.data.message + " error occurred - check the console for details.");
                    });
        }
    },

    // other modules
    mixins: []

});

// import the navigation menu component
import { navigationMenu } from './navigation-menu.js';
// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main"); 