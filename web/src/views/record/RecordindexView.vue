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
                        <td><span style="display: inline-block; width:330px; white-space: nowrap; overflow: hidden;text-overflow: ellipsis;">{{ file.fileName }}</span></td>
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
    <div class="action-buttons">
        <button class="btn btn-wordcount" @click="CommandOfWrodCount">词频统计</button>
        <button class="btn btn-reset" @click="resetwordcount">重置统计</button>
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
                HdfsPath: '/output'
            },
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
        download(filePath) {
            $.ajax({
                url: 'http://localhost:3001/hadoop/downloadfile/',
                type: 'post',
                data: {
                    HdfsFilePath: filePath,
                    loaclFilePath: 'C:\\Users\\20624\\Desktop\\' // 你的本地路径,默认下载到桌面
                },
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                success: (resp) => {
                    alert(resp.message)
                },
                error: (resp) => {
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

        CommandOfWrodCount() {
            $.ajax({
                url: 'http://localhost:3001/command/wordcount',
                type: 'get',
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                success: (resp) => {
                    alert(resp.message)
                },
                error: (resp) => {
                    console.error(resp)
                }
            })
        },
        resetwordcount() {
            $.ajax({
                url: 'http://localhost:3001/hadoop/resetwordcount',
                type: 'get',
                headers: {
                    Authorization: 'Bearer ' + this.$store.state.user.token
                },
                success: (resp) => {
                    alert(resp.message)
                },
                error: (resp) => {
                    console.error(resp)
                }
            })
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
    overflow: auto;
    box-sizing: border-box;
}

div.action-buttons {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.btn-wordcount,
.btn-reset {
    margin: 0 5px;
    background-color: #7156cf;
    color: white;
    padding: 8px 12px;
    width: auto;
}
   

div.file-list {
    max-height: 50vh;
    /* 设置文件列表最大高度为300px */
    overflow-y: auto;
    /* 添加滚动条，超出高度时显示滚动条 */
}



.btn-download {
    background-color: #4CAF50;
    color: white;
}

.btn-delete {
    margin-left: 15%;
    background-color: #f44336;
    color: white;
}</style>
