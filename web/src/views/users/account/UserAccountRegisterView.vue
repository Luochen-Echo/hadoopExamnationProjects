<template>
    <div class="custom-content-field">
    <ContentField>
        <div class="row justify-content-center">
            <div class="col-12 col-md-6">

                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
                    </div>
                    <div class="error_message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">提交</button>
                
                </form>
            </div>
        </div>
    </ContentField>
</div>
    </template>
    
    <script>
    import ContentField from '../../../components/ContentField.vue'
    import { ref } from 'vue'
    import router from '@/router/index'
    import $ from 'jquery'

    export default {
        components: {
            ContentField
        },
        setup() {
           let username = ref("");
           let password = ref("");
           let confirmedPassword = ref("");
           let error_message = ref("");
           const register = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/user/account/register",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmPassword: confirmedPassword.value,
                },
                success(resp) {
                    console.log(resp);
                    if (resp.error_message === "success") {
                        router.push({name: 'user_account_login'});
                    } else {
                        error_message.value = resp.error_message;
                    }
                },
                
            });
           }

           return {
                username,
                password,
                confirmedPassword,
                error_message,
                register
           }
        }
    }
    
    </script>
    
    <style scoped>
    button {
    width: 100%;
}

div.error_message {
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
    height: 350px;
    margin: 0 auto; /* 居中对齐 */
    padding: 0 15px; /* 左右内边距 */
    box-sizing: border-box; /* 确保内边距不影响宽度 */
}
button {
    margin-top: 50px; /* 调整上边距的值来移动按钮的位置 */
}
</style>