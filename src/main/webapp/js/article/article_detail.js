var vm = new Vue({
    el: '#main-container',
    data: {
        obj:{

        }
    },
    methods: {

    },
    created: function () {
        this.obj = parent.layer.obj;
    }

});