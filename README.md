# photo-of-the-day
Luyện tập sử dụng ORM Hibernate

Mô tả
Truy cập https://api.nasa.gov/ và tạo một API key để sử dụng.

Sử dụng phần HTML sau để tạo ra một view có khả năng lấy về "Bức ảnh của ngày" từ kho dữ liệu NASA, lưu ý thay các dấu * bằng api key đã tạo ra ở trên:

<img id="img-of-the-day">
<script>
  let query = 'https://api.nasa.gov/planetary/apod?api_key=**********************;
  let request = new XMLHttpRequest();
  request.open('GET', query);
  request.onload = function() {
    if (request.status === 200) {
      let response = JSON.parse(request.responseText);
      document.getElementById('img-of-the-day').setAttribute('src', response.url);
    }
  };
  request.send();
</script>
