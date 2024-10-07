import Vue from 'vue';
import ViewUI from 'view-design';
import Util from '../libs/util';
import VueRouter from 'vue-router';
import Cookies from 'js-cookie';
import { routers } from './router';

Vue.use(VueRouter);
const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}

const RouterConfig = {
    // mode: 'history',
    routes: routers
};

export const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    //next();
    // console.log("Navigating to:", to.name);
    // console.log("User info from cookies:", Cookies.get('userInfo'));
     ViewUI.LoadingBar.start();
    console.log("routers",routers);
    Util.title(to.meta.title);
    var name = to.name;
    // next();
    if (Cookies.get('locking') == '1' && name !== 'locking') {
        console.log("locking");
        next({
            replace: true,
            name: 'locking'
        });
        return ;
    } else if (Cookies.get('locking') == '0' && name == 'locking') {
        console.log("false");
        next(false);
        return ;
    } else {
        if (!Cookies.get('userInfo') && (name != 'login' && name != 'regist')) {
            console.log("to login");
            next({
                name: 'login'
            });
            return;
        }else{
            if (Cookies.get('userInfo') && name == 'login') {
                console.log("to home_index");
                Util.title();
                next({
                    name: 'home_index'
                });
                return;
            }else{
                console.log("default");
                next();
                // Util.toDefaultPage([...routers], name, router, next);
                return;
            }

        }
    }
});

router.afterEach((to) => {
    console.log("Final navigation to:", to.name);
    Util.openNewPage(router.app, to.name, to.params, to.query);
    ViewUI.LoadingBar.finish();
    window.scrollTo(0, 0);
});
