var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params: {
            pageNum: '',
            pageSize: '',
            realName: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: "/xbjy/user/list",
                method: "post",
                data: this.params
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });

        },
        toDetail: function (id) {
            axios({
                url: "/xbjy/user/selectByUId",
                params: {id: id}
            }).then(res => {
                layer.obj = res.data;
                let index = layer.open({
                    type: 2,
                    title: "用户详细信息",
                    content: '/html/user_detail.html',
                    area: ['80%', '80%']
                });
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {

        },
        toDelete: function (id) {

        },
        deleteById: function () {

        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});