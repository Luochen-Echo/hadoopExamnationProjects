import { createRouter, createWebHistory } from 'vue-router'
import PkindexView from '../views/pk/PkindexView.vue'
import RecordindexView from '../views/record/RecordindexView.vue'
import RanklistindexView from '../views/ranklist/RanklistindexView.vue'
import UserBotindexView from '../views/users/bot/UserBotindexView.vue'
import NotFound from '../views/error/NotFound.vue'
import UserAccountLoginView from '../views/users/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '../views/users/account/UserAccountRegisterView.vue'
import store from '../store'


const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/pk/",
    name: "pk_index",
    component: PkindexView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/record/",
    name: "record_index",
    component: RecordindexView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/ranklist/",
    name: "ranklist_index",
    component: RanklistindexView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/user/bot/",
    name: "user_bot_index",
    component: UserBotindexView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/:pathMatch(.*)*",
    name: "404",
    component: NotFound
  },

  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false
    }
  },

  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false
    }
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {

  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({
      name: "user_account_login"
    })
  } else {
    next();
  }
})

export default router
