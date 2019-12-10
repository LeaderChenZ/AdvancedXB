var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 3
        },
        focus: {
            pageNum: '',
            pageSize: ''
        }
    },
    methods: {

        selectAll: function (pageNum, pageSize) {
            this.focus.pageNum = pageNum;
            this.focus.pageSize = pageSize;
            axios({
                url: "/xbjy/user/selectFocus",
                method: "post",
                data: this.focus
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });

        },

        deleteById: function (id) {
            axios({
                url: "/xbjy/user/chooseFocus",
                params: {fid: id,checked:false}
            }).then(res => {
                if (res.data.success) {
                    layer.msg(res.data.msg);
                    this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
                } else {
                    layer.msg(res.data.msg)
                }
            }).catch(function (error) {
                console.log(error)
            });
        },

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});