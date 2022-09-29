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
var total = 0;
const app = Vue.createApp({

    data() {
        return {
            // models (comma separated key/value pairs)

        };
    },
    
    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer',
        quantity: 'quantityPurchased'
    }),
    
    mounted() {
        // semicolon separated statements

    },
    
    methods: {
        // comma separated function declarations

        getItemTotal(item) {
            var itemTotal = item.salePrice * item.quantityPurchased;
            total += itemTotal;
            return itemTotal;
        },
        
        getTotal() {
            return total;
        },
        
        checkOut() {
            let sale = new Sale(this.customer, this.items);
            if(this.items.length != 0){
                axios.post(salesApi, sale)
                        .then(() => {
                            dataStore.commit("clearItems", sale);
                            window.location = 'view-products.html';
                        })
                        .catch(error => {
                            alert("checkOut: " + error.response.data.message + " error occurred - check the console for details.");
                        });
            }else{
                alert("checkOut: You have zero items in your cart to checkout - check the console for details.");
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
import { dataStore } from './data-store.js';
app.use(dataStore);
        
import { NumberFormatter } from './number-formatter.js';

// mount the page - this needs to be the last line in the file
app.mount("main");