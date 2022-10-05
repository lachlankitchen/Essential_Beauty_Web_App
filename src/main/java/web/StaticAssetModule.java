package web;

import io.jooby.Jooby;
import io.jooby.Route;
import java.nio.file.Paths;

public class StaticAssetModule extends Jooby {

    public StaticAssetModule() {

        // home page
        assets("/", Paths.get("static/index.html"));

        // html files
        assets("/index.html", Paths.get("static/index.html"));
        assets("/sign-in.html", Paths.get("static/sign-in.html"));
        assets("/view-products.html", Paths.get("static/view-products.html"));
        assets("/buy-product.html", Paths.get("static/buy-product.html"));
        assets("/cart.html", Paths.get("static/cart.html"));
        assets("/create-account.html", Paths.get("static/create-account.html"));
        assets("/404.html", Paths.get("static/404.html"));
        assets("/500.html", Paths.get("static/500.html"));

        // css files
        assets("/css/style.css", Paths.get("static/css/style.css"));

        // JavaScript files
        assets("/js/index.js", Paths.get("static/js/index.js"));
        assets("/js/data-store.js", Paths.get("static/js/data-store.js"));
        assets("/js/navigation-menu.js", Paths.get("static/js/navigation-menu.js"));
        assets("/js/create-account.js", Paths.get("static/js/create-account.js"));
        assets("/js/sign-in.js", Paths.get("static/js/sign-in.js"));
        assets("/js/authentication.js", Paths.get("static/js/authentication.js"));
        assets("/js/view-products.js", Paths.get("static/js/view-products.js"));
        assets("/js/buy-product.js", Paths.get("static/js/buy-product.js"));
        assets("/js/number-formatter.js", Paths.get("static/js/number-formatter.js"));
        assets("/js/cart.js", Paths.get("static/js/cart.js"));
        assets("/js/authentication.js", Paths.get("static/js/authentication.js"));

        // external JavaScript files
        assets("/js/external/vue.global.js", Paths.get("static/js/external/vue.global.js"));
        assets("/js/external/vuex.global.js", Paths.get("static/js/external/vuex.global.js"));
        assets("/js/external/vuex-persistedstate.js", Paths.get("static/js/external/vuex-persistedstate.js"));
        assets("/js/external/axios.js", Paths.get("static/js/external/axios.js"));

    }
}
