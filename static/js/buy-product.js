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
            dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
            window.location = 'view-products.html';
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

