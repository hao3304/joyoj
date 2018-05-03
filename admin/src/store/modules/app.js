import {otherRouter, appRouter} from '@/router/router';

const app = {
    state: {
        menuList: [],
        routers: [
            otherRouter,
            ...appRouter
        ],
        token: ''
    },
    mutations: {
        updateMenulist (state) {
            state.menuList = appRouter;
        },
        setToken (state, token) {
            state.token = token;
        }
    }
};

export default app;
