const tagRemoveButtons = document.querySelectorAll('.x')
const tagList = document.querySelector('.tag-list ul')

const xhr = new XMLHttpRequest()

xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText
        tagList.innerHTML = res
    }
}

tagList.addEventListener('click', function (event) {
    let tagId = event.target.previousElementSibling.value
    let reviewId = event.target.previousElementSibling.previousElementSibling.value
    removeTopic(tagId, reviewId);
})


function removeTopic(tagId, reviewId) {
    xhr.open('POST', '/remove-tag/' + tagId + '/' + reviewId)
    xhr.send()
}

