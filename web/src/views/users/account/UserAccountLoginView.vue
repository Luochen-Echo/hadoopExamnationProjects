<template>
    <div class="custom-content-field">
    <ContentField v-if="!$store.state.user.pulling_info" >
        <div class="row justify-content-center">
            <div class="col-12 col-md-6">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </ContentField>
</div>
</template>

<script>
import ContentField from '../../../components/ContentField.vue'
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from '../../../router/index'

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("getinfo", {
                success() {
                    router.push({ name: "home" });
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    store.commit("updatePullingInfo", false);
                }
            })
        } else {
            store.commit("updatePullingInfo", false);
        }

        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                    store.dispatch("getinfo", {
                        success() {
                            router.push({ name: 'home' });
                        }
                    })
                },
                error() {
                    error_message.value = "用户名或密码错误";
                }
            })
        }

        return {
            username,
            password,
            error_message,
            login,
        }
    }
}
</script>

<style scoped>
button {
    width: 100%;
}
div.error-message {
    color: red;
}
.custom-content-field {
    /* 示例：调整外部容器位置和宽度 */
    display: flex;
    justify-content: center;
    align-items: center;
    height: 70vh;
    width: 35vw;
    margin-left: 30%;
}

.custom-content-field .row {
    /* 示例：调整内部行的样式 */
    margin-top: 20px;
}

.custom-content-field .col-12 {
    /* 示例：调整内部列的样式 */
    max-width: 600px;
    height: 250px;
    margin: 0 auto; /* 居中对齐 */
    padding: 0 15px; /* 左右内边距 */
    box-sizing: border-box; /* 确保内边距不影响宽度 */
}
button {
    margin-top: 30px; /* 调整上边距的值来移动按钮的位置 */
}

</style>
