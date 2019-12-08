var vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            params: {}


        }
    },
    methods: {
        selectAll: function () {
            axios({
                url: "/xbjy/dept/index"
            }).then(res => {
                this.params = res.data;
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {
        this.selectAll();
    }


});