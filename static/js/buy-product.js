"use strict";

class SaleItem {
    constructor(product, quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.listPrice;
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

        addToCart() {
            console.log(this.product.quantityInStock > this.quantity);
            if(this.product.quantityInStock > this.quantity){
                dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
                window.location = 'view-products.html';
            }else{
                alert("Unfortunately, there is not enough stock of this product, please try another amount")
            }
        }
    },

    // other modules
    mixins: [NumberFormatter]

});

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

import { NumberFormatter } from './number-formatter.js';

// attach the controller to the <main> tag
app.mount("main");

