$(document).ready(function(){
    $("#boardList").click(function(){
        window.location.href = "/board/list";
})
    })

$(document).ready(function (){
    $("#delete").click(function (){
        console.log('id의 값 : ' + $(this).data("id"))
        var data = {
            id: $(this).data("id")

        };
        $.post('/board/deleteOk', data, function (response){
            alert('정상적으로 보내졌습니다.' + '해당 요소 ID 값 : ' + data.id);
            window.location.href = '/board/list';
        });
})
})


// 댓글 엔터 버튼 기능 활성화
$(document).ready(function (){
    $('#commentI').on('focus', function (){
        $(this).on('keydown', function (e){
            if (e.keyCode === 13) {
                e.preventDefault();
                $('#submitButton').click();
            }
        })
    })
})






// $(document).ready(function (){
//     $("#update").click(function (){
//         var data = {
//             id : $(this).data("id")
//         }
//         $.post('/board/update/'+ data.id, data , function (response){
//                 console.log("성공적으로 이동 완료.")
//         })
//     }
//
// )
// })