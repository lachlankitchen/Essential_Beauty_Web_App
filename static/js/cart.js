"use strict";

class SaleItem {
    constructor(product, quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.listPrice;
    }
}

class Sale {
    constructor(customer, items) {
        this.customer = customer;
        this.items = items;
    }
}


var salesApi = '/api/sales';

const app = Vue.createApp({

    data() {
        return {
            // models (comma separated key/value pairs)

        };
    },

    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),

    mounted() {
        // semicolon separated statements


    },

    methods: {
        // comma separated function declarations

        checkOut() {
            let sale = new Sale(this.customer, this.items);

            axios.post(salesApi, this.sale)
                    .then(() => {
                        window.location = 'view-products.html';
                    })
                    .catch(error => {
                        alert("checkOut: " + error.response.data.message + " error occurred - check the console for details.");
                    });
        }

    }

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

// mount the page - this needs to be the last line in the file
app.mount("main");