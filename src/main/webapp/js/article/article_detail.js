var vm = new Vue({
    el: '#main-container',
    data: {
        obj:{

        }
    },
    methods: {
        addArticle:function (id) {
            axios({
                url: "/xbjy/article/add",
                params:{aid:id}
            }).then(res => {
                parent.layer.msg(res.data.msg);
               let index= parent.layer.getFrameIndex(window.name);
               parent.layer.close(index);
            }).catch(function (error) {
                console.log(error)
            })
        },
        deleteArticle:function (id) {
            axios({
                url: "/xbjy/article/delete",
                params:{aid:id}
            }).then(res => {
                parent.layer.msg(res.data.msg);
                let index= parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {
        this.obj = parent.layer.obj;
    }

});