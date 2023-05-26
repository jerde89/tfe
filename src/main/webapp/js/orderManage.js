var feedbackNonLusAjax;
var feedbackLusAjax;


$(document).ready(function () {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
    });
    feedbackNonLusAjax = $('#feedback_non_lus_ajax').DataTable({
        ajax: {url: '/feedbackList/unread', dataSrc: ""},
        columns: [
            {data: 'firstnameFeedback'},
            {data: 'nameFeedback'},
            {data: 'emailFeedback', render: transformToMail},
            {data: 'phoneFeedback'},
            {data: 'commentFeedback', render: minText10},
            {data: 'ActionsFeedback', render: makeActionNonlus},

        ],
        order:[[5,'desc']],

        "language": languageConfig
    });



    function makeActionNonlus(data, type, full, meta) {
        return '<div class="row"><div class="col-4 text-center">\n' +
            '                                         <button onclick="togglePopup(' + full.feedbackId + ',\'actionNonLu\')" data-toggle="tooltip" data-placement="top" title="Voir la commande" >\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">\n' +
            '                                                    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>\n' +
            '                                                    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>\n' +
            '                                                </svg>' +
            '                                                   \n' +
            '                                         </button>\n' +
            '                                    </div>\n'

    }


    function minText10(data, type, full, meta) {
        var strReturn = data.substring(0, 10);
        strReturn += data.length > 9 ? '...' : '';
        return strReturn;
    }

    function transformToMail(data) {
        return '<a href="mailto:' + data + '"/>' + data + '</a>';
    }

    feedbackLusAjax = $('#feedback_lus_ajax').DataTable({
        ajax: {url: '/feedbackList/read', dataSrc: ""},
        columns: [
            {data: 'firstnameFeedback'},
            {data: 'nameFeedback'},
            // {data: 'emailFeedback'},
            // {data: 'phoneFeedback'},
            {data: 'commentFeedback', render: minText10},
            {data: 'createdAtFeedback', render: DataTable.render.datetime('DD/MM/YYYY HH:mm')},
            {data: 'updateAtFeedback', render: DataTable.render.datetime('DD/MM/YYYY HH:mm')},
            {data: 'ActionsFeedback', render: makeActionlus},
        ],
        order:[[3,'desc']],
        "language": languageConfig
    });

    function makeActionlus(data, type, full, meta) {
        return '<div class="row"><div class="col-4 text-center">\n' +
            '                                         <button onclick="togglePopup(' + full.feedbackId + ', \'lu\')" data-toggle="tooltip" data-placement="top" title="Voir le feedback" >\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">\n' +
            '                                                    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>\n' +
            '                                                    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>\n' +
            '                                                </svg>' +
            '                                                   \n' +
            '                                         </button>\n' +
            '                                    </div>\n' +
            '                                    <div class="col-4 text-center">\n' +
            '                                        <button type="submit" data-toggle="tooltip" data-placement="top" title="Marquer comme non lu" onclick="callAjaxIsNotReadFeedback(' + full.feedbackId + ')">\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope-x" viewBox="0 0 16 16">\n' +
            '                                                   <path d="M2 2a2 2 0 0 0-2 2v8.01A2 2 0 0 0 2 14h5.5a.5.5 0 0 0 0-1H2a1 1 0 0 1-.966-.741l5.64-3.471L8 9.583l7-4.2V8.5a.5.5 0 0 0 1 0V4a2 2 0 0 0-2-2H2Zm3.708 6.208L1 11.105V5.383l4.708 2.825ZM1 4.217V4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v.217l-7 4.2-7-4.2Z"/>\n' +
            '                                                   <path d="M16 12.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Zm-4.854-1.354a.5.5 0 0 0 0 .708l.647.646-.647.646a.5.5 0 0 0 .708.708l.646-.647.646.647a.5.5 0 0 0 .708-.708l-.647-.646.647-.646a.5.5 0 0 0-.708-.708l-.646.647-.646-.647a.5.5 0 0 0-.708 0Z"/>\n' +
            '                                                </svg>\n' +
            '                                            </button>\n' +
            '\n' +
            '                                    </div>\n' +
            '\n' +
            '                                    <div class="col-4 text-center">\n' +
            '\n' +
            '                                            <button  data-toggle="tooltip" type="submit" data-toggle="tooltip" data-placement="top" title="Supprimer le feedback" onclick="actionDeleteFeedback(' + full.feedbackId + ')">\n' +
            '                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">\n' +
            '                                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>\n' +
            '                                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>\n' +
            '                                                </svg>\n' +
            '                                            </button>\n' +
            '\n' +
            '                                    </div></div> ';
    }
});
