var setting = {
    check: {
        enable: true,
        chkboxType: { "Y": "ps", "N": "ps" }
    },
    view: {
        dblClickExpand: false,
        showLine: false,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            // idKey: "id",
            // pIdKey: "parentId",
            rootPId: "0"
        },
        key: {
            checked: "checked",
            url: "xurl"
        }
    },
    callback: {
        onClick: onClick
    }
};

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    zTree.expandNode(treeNode);
}