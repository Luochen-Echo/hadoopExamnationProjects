<template>
    <div class="display container">
        <div class="file-list">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">文件名</th>
                        <th scope="col">大小</th>
                        <th scope="col">副本数量</th>
                        <th scope="col">上次修改时间</th>
                        <th scope="col">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="file in fileList" :key="file.filePath">
                        <td><span
                                style="display: inline-block; width:330px; white-space: nowrap; overflow: hidden;text-overflow: ellipsis;">{{
                                    file.fileName }}</span></td>
                        <td>{{ file.fileSize }}</td>
                        <td>{{ file.numReplicas }}</td>
                        <td>{{ file.lastModifiedTime }}</td>
                        <td>
                            <button class="btn btn-download" @click="download(file.filePath)">下载</button>
                            <button class="btn btn-delete" @click="deleteFile(file.filePath)">删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
    <div class="upload">
        <input type="file" ref="fileInput" @change="uploadFile" style="display: none" />
        <button class="btn btn-upload" @click="clickUploadButton">上传文件</button>
    </div>
</template>

<script>
import $ from 'jquery'

export default {
    data() {
        return {
            fileList: []
        }
    },
    mounted() {
        $.ajax({
            url: 'http://localhost:3001/hadoop/listfile/', // 替换为你的后端接口
            type: 'get',
            data: {
                HdfsPath: '/'
            }
            ,
            headers: {
                Authorization: 'Bearer ' + this.$store.state.user.token
            },
            success: (resp) => {
                this.fileList = resp
            },
            error: (resp) => {
                console.error(resp)
            }
        })
    },
    methods: {
        // download(filePath) {
        //     $.ajax({
        //         url: 'http://localhost:3001/hadoop/downloadfile/',
        //         type: 'post',
        //         data: {
        //             HdfsFilePath: filePath,
        //             loaclFilePath: 'C:\\Users\\20624\\Desktop\\' // 你的本地路径,默认下载到桌面
        //         },
        //         headers: {
        //             Authorization: 'Bearer ' + this.$store.state.user.token
        //         },
        //         success: (resp) => {
        //             alert(resp.message)
        //         },
        //         error: (resp) => {
        //             console.error(resp)
        //         }
        //     })
        // },
        download(filePath) {
            $.ajax({
                url: 'http://localhost:3001/hadoop/download/',
                type: 'post',
                data: {
                    hdfsFilePath: filePath, // 确保此处的变量名与你的后端接口匹配
                },
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                xhrFields: {
                    responseType: 'blob' // 设置这一行将告诉jQuery，我们期望的响应类型为 Blob
                },
                success: function (resp) {
                    // 创建一个新的 Blob 对象使用下载的数据
                    let blob = new Blob([resp]);
                    let link = document.createElement('a');

                    // 使用 window.URL.createObjectURL() 方法创建对象的 URL
                    link.href = window.URL.createObjectURL(blob);

                    // 解析出文件名
                    let fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

                    // 设置下载的文件名
                    link.download = fileName; // 使用你需要的文件名和扩展名

                    // 添加链接到页面，触发 'click' 事件开始下载
                    document.body.appendChild(link);
                    link.click();

                    // 清理：在下载开始后，移除链接并释放对象 URL
                    document.body.removeChild(link);
                    window.URL.revokeObjectURL(link.href);
                },
                error: function (resp) {
                    console.error(resp)
                }
            })
        },

        deleteFile(filePath) {
            $.ajax({
                url: 'http://localhost:3001/hadoop/delfile/',
                type: 'delete',
                data: {
                    filePath: filePath
                },
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                success: (resp) => {
                    alert(resp.message)
                    if (resp.message === "删除成功") {
                        this.fileList = this.fileList.filter(file => file.filePath !== filePath)
                    }
                },
                error: (resp) => {
                    console.error(resp)
                }
            })
        },
        clickUploadButton() {
            this.$refs.fileInput.click();
        },

        uploadFile() {
            if (!this.$refs.fileInput.files.length) {
                alert('请先选择文件!');
                return;
            }
            const file = this.$refs.fileInput.files[0]; // 获取选中的文件
            const formData = new FormData(); // 创建一个 FormData 对象用于打包文件数据
            formData.append('file', file); // 将文件添加到 FormData 对象中
            $.ajax({
                url: 'http://localhost:3001/hadoop/uploadfile/',
                type: 'post',
                data: formData,
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                processData: false, // 告诉 jQuery 不要处理发送的数据
                contentType: false, // 告诉 jQuery 不要设置 Content-Type 请求头
                success: (resp) => {
                    alert(resp.message);
                    window.location.reload(); // 上传成功后刷新页面
                },
                error: (resp) => {
                    console.error(resp);
                }
            });
        },
    }
}
</script>

<style scoped>
div.display {
    background-color: rgb(255, 255, 255);
    width: 70vw;
    height: 60vh;
    margin: 40px auto;

}


div.upload {
    text-align: center;
    /* 居中对齐上传按钮 */
    margin-bottom: 20px;
    /* 设置下边距为20px */
}

div.file-list {
    max-height: 410px;
    /* 设置文件列表最大高度为300px */
    overflow-y: auto;
    /* 添加滚动条，超出高度时显示滚动条 */
}

.btn-upload {
    background-color: #7156cf;
    color: white;
    padding: 10px 20px;
    /* 设置按钮内边距为10px上下，20px左右 */
}

.btn-download {
    background-color: #4CAF50;
    color: white;
}

.btn-delete {
    margin-left: 5%;
    background-color: #f44336;
    color: white;
}</style>
