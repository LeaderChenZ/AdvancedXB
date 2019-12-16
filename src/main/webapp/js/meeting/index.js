var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 3
        },
        params:{
            title:'',
            status:''
        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            console.log(this.params.status)
            axios({
                url: "/xbjy/meeting/index",
                method: "post",
                data: this.params
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });
        },
        selectDetail:function (id) {
            axios({
                url: "/xbjy/meeting/selectDetail",
                params:{
                    mid:id
                }
            }).then(res => {
                if (res.data.status == 1) {
                layer.obj = res.data;
                let index = layer.open({
                    type: 2,
                    title: "会议信息",
                    content: '/html/meeting_detail.html',
                    area: ['80%', '80%']
                })
                }else {
                    layer.msg("会议未开始或已结束！")
                }
            }).catch(function (error) {
                console.log(error)
            });
        }

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});