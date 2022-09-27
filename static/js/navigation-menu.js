"use strict";

export const navigationMenu = {

    data() {
        return {
            // models map (comma separated key/value pairs)
            
        };
    },

    computed: {
        signedIn() {
            return this.customer != null;
        },
        ...Vuex.mapState({
                customer: 'customer'
        })
    },

    template:
            `
            <div class="page">
                <nav class="page__menu menu">
                    <ul class="menu__list r-list">
                        <a class="menu__link r-link text-underlined" href=".">Home</a>
                        <a class="menu__link r-link text-underlined" href="view-products.html" v-if="signedIn">Browse Products</a>
                        <a class="menu__link r-link text-underlined" href="cart.html" v-if="signedIn">View Cart</a>
                        <a class="menu__link r-link text-underlined" href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
                        <a class="menu__link r-link text-underlined" href="sign-in.html" v-if="!signedIn">Sign In</a>
                        <a class="menu__link r-link text-underlined" href="create-account.html" v-if="!signedIn">Register</a>
                    </ul>
                </nav>
            </div>
            <div id="welcome" class="center" v-if="signedIn">Welcome {{customer.firstName}}</div>

        `,

    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};
