# Dictionary
* Bài tập lớn OOP của Nguyễn Thị Thư (19020449).
* Project viết bằng java trên IntelliJ, sử dụng jdk8.
* Sử dụng database hơn 100.000 từ.
* Sử dụng API Speech và API Translator của Microsoft Azure.
# Run Project
* Tải về và giải nén, mở bằng IntelliJ.
* Chuột phải vào thư mục src -> Mark Directory as -> Sources Root.
* Chọn File/Project Structure/ Modules / Dependecies -> Click vào dấu cộng và add các file rar trong thư mục libraries.
* Add file freetts.jar, jsapi.jar trong freetts-1.2/lib.
* Reload file pom.xml để cập nhật thư viện bằng Maven.

# Các chức năng
* Dictionary: Tra cứu từ điển Anh Việt, sử dụng sqlite để lấy database trên mạng có hơn 100.000 từ
* Ấn vào audio để phát âm, có 2 kiểu phát âm, bằng thư viện freetts(offline) hoặc bằng API Speech của Microsoft Azure(online)
* Ấn vào dấu cộng để thêm từ đang tra vào My Vocab (danh sách những từ yêu thích)
* Translate: Dịch đoạn văn bản Anh - Việt hoặc Việt - Anh bằng API Translator của Microsoft Azure (online)
* My vocab: Danh sách những từ yêu thích
* Any Change: Thêm, sửa, xóa từ mới
