-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 19, 2025 lúc 06:58 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookapp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `cover_url` varchar(500) DEFAULT NULL,
  `file_url` varchar(500) DEFAULT NULL,
  `uploaded_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`book_id`, `category_id`, `title`, `author`, `description`, `cover_url`, `file_url`, `uploaded_at`) VALUES
(1, 1, 'Chat Với René Descartes', 'Bùi Văn Nam Sơn', 'Một cuộc trò chuyện giả tưởng giữa tác giả cùng với René René Descartes – một nhà toán học; một triết gia lỗi lạc, được xem là cha đẻ của triết học hiện đại. Cách viết hài hước, giải thích những tư tưởng của Descartes một cách đơn giản, giúp bạn đọc tiếp cận với triết học của học giả lớn dễ dàng hơn. Và qua đó, bạn đọc cũng có thể hiểu thêm về Descartes, một con người lánh đời, luôn mang nhiều ước vọng và luôn nỗ lực để phá bỏ sự chi phối hoàn toàn của phái Kinh viện trong Triết học.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-chat-voi-rene-descartes.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-chat-voi-rene-descartes.pdf', '2025-05-03 18:01:41'),
(2, 1, 'Con Người Trong Thế Giới Tinh Thần', 'Nikolai Alexandrovich Berdyaev', 'N. A. Berdyaev cho rằng không thể có bản diện cá nhân nếu không tồn tại cái đứng cao hơn nó, nếu không có cái thế giới trên núi để bản diện cá nhân leo lên. Hiện hữu của bản diện cá nhân đòi hỏi phải có hiện hữu của các giá trị siêu cá biệt. Từ nhân học ông đi đến hiện hữu của Thượng Đế. Nhưng N. A. Berdyaev bác bỏ quan niệm Thượng Đế như sức mạnh thống trị thế gian và sử dụng con người vốn là tạo vật của mình như phương tiện để tự vinh danh mình. Ông quan niệm một Thượng Đế-bản diện cá nhân mong mỏi con người-bản diện cá nhân đáp lại lời hiệu triệu của Người và Người có thể giao lưu tình yêu với nó. Thượng Đế bộc lộ bản thân mình trong thế giới tinh thần của con người, nhưng Thượng Đế không cai quản thế gian như một quân vương. N. A. Berdyaev tự xem mình là tín đồ Kitô giáo, nhưng không ràng buộc bản thân với bất cứ giáo hội nào. Ông cho rằng cuộc sống tôn giáo bao giờ cũng là cuộc sống cá nhân riêng tư trong thâm nhập vào chiều sâu của nó. Ông viết rằng từ thời thơ ấu ông đã xác định kiểu tôn giáo của ông là tinh thần nội tâm và tự do.\r\n\r\nBerdyaev cho rằng có ba trạng thái của con người, ba cấu trúc của ý thức có thể hàm nghĩa như “ông chủ”, “kẻ nô lệ” và “người tự do”. Ông chủ và kẻ nô lệ có tính tương liên, chúng không thể hiện hữu người này không có người kia. Còn người tự do hiện hữu tự thân nó, nó có trong bản thân mình phẩm chất riêng của nó mà không có tính tương liên với cái đối lập với nó. Ông chủ là ý thức hiện hữu cho bản thân mình, nhưng là ý thức hiện hữu cho bản thân mình thông qua kẻ khác, thông qua kẻ nô lệ. Nếu như ý thức của ông chủ là ý thức hiện hữu của kẻ khác cho bản thân mình, thì ý thức của nô lệ là ý thức hiện hữu của bản thân mình cho kẻ khác. Còn ý thức của người tự do là ý thức hiện hữu của mỗi người cho bản thân mình, nhưng tự do bước ra khỏi bản thân mình đi đến với kẻ khác và đi đến với tất cả mọi người. Giới hạn tột cùng của tình trạng nô lệ là tình trạng không có ý thức của nó. Thế giới của tình trạng nô lệ là thế giới tinh thần xa lạ với bản thân mình. Ngoại hiện hóa là nguồn gốc của tình trạng nô lệ. Tự do là nội hiện hóa\r\n\r\nBerdyaev cho rằng thống trị là mặt trái của tình trạng nô lệ. Con người không được trở thành ông chủ, mà phải là người tự do. Plato đã nhận xét rằng chính bạo chúa cũng là kẻ nô lệ. Nô dịch kẻ khác cũng là nô dịch bản thân mình. Ý chí vươn tới hùng mạnh bao giờ cũng là ý chí nô lệ. César, vị anh hùng của chủ nghĩa đế quốc, là kẻ nô lệ, nô lệ của thế gian, nô lệ của ý chí vươn tới hùng mạnh, nô lệ của khối đông người mà thiếu khối đông người ấy thì ông ta không thể thực hiện được ý chí vươn tới hùng mạnh. Ông chủ chỉ biết đến chiều cao mà những kẻ nô lệ nâng ông ta lên, César chỉ biết đến chiều cao mà đám quần chúng nâng ông ta lên. Thế nhưng những kẻ nô lệ, đám quần chúng, cũng quăng xuống tất cả các ông chủ, tất cả các César. Berdyaev nhấn mạnh: “Tự do là tự do không phải chỉ thoát khỏi các ông chủ, mà còn thoát khỏi các nô lệ nữa. Ông chủ bị hạn định từ bên ngoài, ông chủ không phải là bản diện cá nhân, cũng như kẻ nô lệ không phải là bản diện cá nhân, chỉ có người tự do mới là bản diện cá nhân, dẫu cho toàn bộ thế gian đều muốn nô dịch anh ta”….', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-con-nguoi-trong-the-gioi-tinh-than.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-con-nguoi-trong-the-gioi-tinh-than.pdf', '2025-05-03 18:25:07'),
(3, 1, 'Cách Ta Nghĩ', 'John Dewey', '1. Về tác giả:\r\n\r\nJohn Dewey (1859-1952, Hoa Kỳ) là một nhà triết học thuộc chủ nghĩa thực dụng, nhà tâm lý học và là nhà giáo dục – thường được coi là cha đẻ của phong trào cải cách giáo dục. Năm 1875, Dewey vào học ở Đại học Vermont và nhận bằng cử nhân ở đây. Năm 1894, Dewey chuyển đến Đại học Chicago với vai trò là Trưởng khoa Triết học, Tâm lý và Giáo dục học. Năm 1896, ông thành lập Đại học Thực nghiệm, ngày nay được biết đến nhiều hơn với tên gọi “Trường Dewey”.\r\n\r\nDewey rời Chicago đến Columbia vào năm 1904 do có xung đột về cách quản lý Trường Thực nghiệm. Sau đó, ông trở thành một triết gia, một nhà giáo dục xuất chúng được nhiều người biết tới. Dewey nghỉ hưu vào năm 1930, mặc dù ông tiếp tục giữ cương vị giáo sư danh dự cho đến năm 1939; ông vẫn hoạt động cống hiến không ngừng cho đến khi qua đời trong ngôi nhà của mình ở New York.\r\n\r\n2. Về tác phẩm:\r\n\r\nCách ta nghĩ của John Dewey nói về bản chất tư duy của con người. Thông qua những ví dụ hằng ngày, Dewey mô tả chi tiết các quy trình khác nhau của quá trình suy nghĩ và rèn luyện trí nghĩ. Ông cho rằng suy nghĩ là một hành động tự nhiên, việc dạy một người nào đó suy nghĩ là một việc bất khả. Tuy nhiên, có thể giúp phát triển tư duy của con người thông qua việc phát triển khả năng sáng tạo, sự tò mò và cách đặt câu hỏi. Để có thể làm như vậy, thay vì nhồi nhét kiến thức, chúng ta có nên tập trung vào việc tạo ra một môi trường kích thích tư duy sáng tạo.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cach-ta-nghi.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-cach-ta-nghi.pdf', '2025-05-03 18:25:14'),
(4, 1, 'Chủ Nghĩa Tự Do Truyền Thống', 'Ludwig von Mises', 'Cuốn sách tương đối mỏng này có tầm quan trọng gấp nhiều lần sức tưởng tượng nếu chỉ nhìn vào độ dầy và ngôn ngữ khiêm nhường của nó. Đây là tác phẩm bàn về xã hội tự do, bàn về điều mà hiện nay có thể được gọi là “chính sách” đối nội và đối ngoại cho một xã hội như thế; và đặc biệt là bàn về những trở ngại và khó khăn, cả thực tế lẫn tưởng tượng, trên con đường thiết lập và giữ gìn hình thức tổ chức một xã hội như thế.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-chu-nghia-tu-do-truyen-thong.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-chu-nghia-tu-do-truyen-thong.pdf', '2025-05-07 19:23:01'),
(5, 1, 'Luận Lý Học', 'Trần Bích Lan', 'Logic hình thức còn được biết đến trong toán học như là logic ký hiệu là ngành khoa học nằm trong miền giao thoa giữa toán học và triết học tự nhiên. Logic hình thức sử dụng ký hiệu hình thức và các phép toán đại số cùng với các nguyên tắc nhất định về giá trị chân lý để nhằm xác định tính đúng đắn của các lập luận.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-luan-ly-hoc.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-luan-ly-hoc.pdf', '2025-05-07 19:23:01'),
(6, 1, 'Siêu Lý Tình Yêu', 'Vladimir Soloviev', 'Soloviev chỉ ra 5 loại quan hệ nam nữ với loại quan hệ nam nữ có tình yêu gắn với lý tưởng hóa đối tượng yêu, làm chúng ta khi yêu mang bản nguyên thần thánh. Tình yêu nam – nữ tuyệt đích sẽ bất tử hóa tất cả bởi tình yêu chuyên trở sự sống vĩnh cửu cho cái mình yêu, tái sinh vĩnh viễn trong cái đẹp.\r\n\r\nĐó là luận điểm về tình yêu qua các tác phẩm trứ danh: Siêu lý tình yêu (1892-1893), Chiến tranh và hòa bình, Biện minh cái thiện của Vladimir Solovyev, người nhà văn, đại triết gia Nga – người đặt nền móng cho Triết học Tình yêu châu Âu.\r\n\r\nNgười dịch: Nguyễn Vĩnh Cư', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-sieu-ly-tinh-yeu.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-sieu-ly-tinh-yeu.pdf', '2025-05-07 19:23:01'),
(7, 2, 'Những Hồi Ức Về Sherlock Holmes', 'Sir Arthur Conan Doyle', 'Toàn bộ bộ sưu tập trong Hồi Ức về Sherlock Holmes lần đầu tiên được xuất bản vào ngày 13 tháng 12 năm 1893 bởi công ty G. Newnes Ltd. trong một ấn bản với 10.000 bản, đồng thời đây cũng là phần thứ ba của The Strand Library. Phiên bản đầu tiên tại Mỹ (ấn hành bởi công ty Harper & Brothers, tháng 2 năm 1894) đã giữ lại câu chuyện The Adventure of the Cardboard Box (Hai Lỗ Tai Trong Hộp Cactong), trong khi  phiên bản tiếng Anh đã gỡ bỏ nó.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-nhung-hoi-uc-ve-sherlock-holmes.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-nhung-hoi-uc-ve-sherlock-holmes.pdf', '2025-05-07 21:35:30'),
(8, 2, 'Đảo kinh hoàng', 'Dennis Lehane', 'Đặc vụ FBI Teddy Daniels được cử đến đảo Sutter trên vịnh Massachusetts để điều tra vụ mất tích bí ẩn của người phụ nữ có tên Rachel Solando, bệnh nhân tại khu điều trị tâm thần đặc biệt dành cho tội phạm trên đảo. Nhân chứng là những người điên từ cấp độ nhẹ cho đến cuồng bạo, đã từng gây thảm án. Cộng sự là các bác sĩ hành tung bí ẩn, thù địch. Shutter Island được dựng thành phim năm 2009', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-dao-kinh-hoang.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-dao-kinh-hoang.pdf', '2025-05-07 21:35:30'),
(9, 2, 'Truy tìm Dracula', 'Elizabeth Johnson Kostova', 'Đó là câu chuyện kép, như người trong giới thường nói là “câu chuyện của câu chuyện” với sự đan xen của hai ngôi kể: tôi và cha. “Tôi”, một cô gái không tên 16 tuổi sống thu mình với niềm hạnh phúc lớn nhất là được ở trong thư viện của cha, nơi mà tất cả các cuốn sách đều có lời đề từ “Người kế thừa thân mến và bất hạnh của tôi”. Chẳng ai có thể ngờ rằng ngay chính trong thư viện của người cha tạo dựng, một thế giới sách nhỏ bé tại gia như biết bao thư viện gia đình khác, “tôi” của chúng ta lại có thể chắp nối và lần tìm tới những sự kiện lớn lao vốn đã bị lãng quên trong những năm dài lịch sử.\r\n\r\nTruy tìm Dracula dẫn bạn đọc theo hành trình khám phá bí ẩn của cô gái trẻ, một hành trình dẫn đến tới Istanbul, Budapest và tận cùng của Đông Âu. Giữa vô vàn tu viện, kho lưu trữ, thư viện, những lá thư và những cuộc nói chuyện bí mật, sự thật đáng sợ về triều đại bóng tối của Vlad – “kẻ xiên người” được ráp nối và có vẻ đằng sau đó là một giai đoạn có thật trong lịch sử.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-truy-tim-dracula.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-truy-tim-dracula.pdf', '2025-05-07 21:35:30'),
(10, 2, 'Dấu Bộ Tứ', 'Sir Arthur Conan Doyle', 'Sherlock Holmes là một nhân vật thám tử hư cấu vào cuối thế kỉ 19 và đầu thế kỉ 20, xuất hiện lần đầu trong tác phẩm của nhà văn Arthur Conan Doyle xuất bản năm 1887. Ông là một thám tử tư ở Luân Đôn nổi tiếng nhờ trí thông minh, khả năng suy diễn logic và quan sát tinh tường trong khi phá những vụ án mà cảnh sát phải bó tay.Theo dấu bộ tứ là một vụ án ly kì mà Holmes gặp phải. Chúng ta hãy cùng nghe toàn bộ câu truyện mà bác sĩ Watson người cộng sự của Holmes chuẩn bị kể lại…', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-dau-bo-tu.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-dau-bo-tu.pdf', '2025-05-07 21:35:30'),
(11, 2, 'Tokyo Hoàng Đạo Án', 'Soji Shimada', 'Tokyo hoàng đạo án là tiểu thuyết trinh thám kinh dị đầu tay của ông, đã được vinh danh ở giải Edogawa Rampo vào năm 1981.\r\n\r\nHơn 30 năm qua, Tokyo hoàng đạo án vẫn nằm trong danh sách các tác phẩm văn học bán chạy nhất của Nhật Bản, đã được dịch ra nhiều thứ tiếng như Anh, Pháp, Nga, Trung, Thái Lan, Tagalog…', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tokyo-hoang-dao-an.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-tokyo-hoang-dao-an.pdf', '2025-05-07 21:35:30'),
(12, 3, 'Công Nghệ Blockchain', 'Nhiều tác giả', 'Blockchain là chủ đề đang vô cùng nóng trên toàn cầu hiện nay. Nó cùng với Bitcoin và tiền kỹ thuật số trở thành đề tài bàn luận trên rất nhiều mặt báo và trong những cuộc trò chuyện của mọi người. Tuy nhiên, khi nói về blockchain vẫn còn nhiều tranh cãi. Có người lo lắng rằng Bitcoin có thể chỉ là bong bóng, nhiều người cho rằng công nghệ phía sau nó là một sự đột phá, và công nghệ ấy sẽ tiếp tục con đường của mình cho đến khi được chấp nhận và tích hợp với Internet.\r\n\r\nThậm chí, Jamie Dimon, CEO của JP Morgan, người đã gay gắt phản đối Bitcoin và gây ra nhiều lo lắng cho cộng đồng tiền kỹ thuật số cũng đã đồng ý rằng, công nghệ DLT (công nghệ sổ cái phân tán – distributed ledger technology) có tiềm năng rất lớn để thay đổi ngành tài chính và các ngành khác. Hơn nữa, JP Morgan cùng với nhiều ngân hàng đã tiến hành kiểm tra blockchain cho những trường hợp sử dụng khác nhau trong thực tế.\r\n\r\nVậy thì Blockchain thực chất là gì? Nó có thể ứng dụng vào những lĩnh vực nào trong cuộc sống và tại sao nó lại được quan tâm như vậy?.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cong-nghe-blockchain.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-cong-nghe-blockchain.pdf', '2025-05-07 21:35:53'),
(13, 3, 'Programming ASP.NET MVC 4', 'Jess Chadwick - Todd Snyder - Hrusikesh Panda', 'Get up and running with ASP.NET MVC 4, and learn how to build modern server-side web applications. This guide helps you understand how the framework performs, and shows you how to use various features to solve many real-world development scenarios you’re likely to face. In the process, you’ll learn how to work with HTML, JavaScript, the Entity Framework, and other web technologies.\r\n\r\nYou’ll start by learning core concepts such as the Model-View-Controller architectural pattern, and then work your way toward advanced topics. The authors demonstrate ASP.NET MVC 4 best practices and techniques by building a sample online auction site (“EBuy”) throughout the book.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-programming-asp-net-mvc-4.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-programming-asp-net-mvc-4.pdf', '2025-05-07 21:35:53'),
(14, 3, 'Giải Thuật Và Lập Trình', 'Lê Minh Hoàng', 'Nếu bạn là người đam mê tin học, nếu bạn là người muốn khám phá về lập trình, hẳn bạn phải biết đến một cuốn sách tin học rất nổi tiếng ở Việt Nam trong nhiều năm trở lại đây. Từ những học sinh không chuyên đến những thành viên đội tuyển thi quốc tế tin học, có lẽ không một ai chưa từng học qua cuốn sách được biên soạn bởi một thầy giáo trẻ những đầy tài năng của trường Đại học Sư phạm Hà Nội, thầy Lê Minh Hoàng.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-giai-thuat-va-lap-trinh.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-giai-thuat-va-lap-trinh.pdf', '2025-05-07 21:35:53'),
(15, 3, 'Linux All-In-One For Dummies – 5Th Edition', 'Emmett Dulaney', 'Linux All-in-One For Dummies giải thích mọi thứ bạn cần để bắt đầu và chạy với hệ điều hành Linux phổ biến. Được viết trong phong cách thân thiện và dễ tiếp cận, cuốn sách lý tưởng cho người mới dùng Linux và người đã có một ít kinh nghiệm với hệ điều hành này, cũng như bất kỳ ai đang học chứng chỉ Linux cấp độ 1. Bốn phần bên trong sách bao gồm các vấn đề cơ bản của Linux, làm sao để tương tác với nó, các vấn đề về mạng, dịch vụ Internet, quản trị, bảo mật, kịch bản scripting và chứng chỉ cấp 1.\r\n\r\nBao gồm tất cả mọi thứ người mới bắt đầu cần biết để thâm nhập vào thế giới khổng lồ của Linux, Linux All-in-One For Dummies, 5th Edition là tài nguyên và tài liệu tham khảo cuối cùng cho các chuyên gia tham vọng.\r\nTất cả những gì bạn cần để khởi đầu thu bé lại vừa bằng một quyển sách. Thật là tuyệt vời!', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-linux-all-in-one-for-dummies-5th-edition.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-linux-all-in-one-for-dummies-5th-edition.pdf', '2025-05-07 21:35:53'),
(16, 3, 'Machine Learning Cơ Bản', 'Vũ Hữu Tiệp', 'Những năm gần đây, AI – Artificial Intelligence (Trí Tuệ Nhân Tạo), và cụ thể hơn là Machine Learning (Học Máy hoặc Máy Học) nổi lên như một bằng chứng của cuộc cách mạng công nghiệp lần thứ tư (1 – động cơ hơi nước, 2 – năng lượng điện, 3 – công nghệ thông tin). Trí Tuệ Nhân Tạo đang len lỏi vào mọi lĩnh vực trong đời sống mà có thể chúng ta không nhận ra. Xe tự hành của Google và Tesla, hệ thống tự tag khuôn mặt trong ảnh của Facebook, trợ lý ảo Siri của Apple, hệ thống gợi ý sản phẩm của Amazon, hệ thống gợi ý phim của Netflix, máy chơi cờ vây AlphaGo của Google DeepMind, …, chỉ là một vài trong vô vàn những ứng dụng của AI/Machine Learning.\r\n\r\nMachine Learning là một tập con của AI. Theo định nghĩa của Wikipedia, Machine learning is the subfield of computer science that “gives computers the ability to learn without being explicitly programmed”. Nói đơn giản, Machine Learning là một lĩnh vực nhỏ của Khoa Học Máy Tính, nó có khả năng tự học hỏi dựa trên dữ liệu đưa vào mà không cần phải được lập trình cụ thể. Bạn Nguyễn Xuân Khánh tại đại học Maryland đang viết một cuốn sách về Machine Learning bằng tiếng Việt khá thú vị, các bạn có thể tham khảo bài Machine Learning là gì?.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-machine-learning-co-ban.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-cong-nghe-blockchain.pdfhttps://file.nhasachmienphi.com/pdf/nhasachmienphi-machine-learning-co-ban.pdf', '2025-05-07 21:35:53'),
(17, 3, 'Learning Vue.js 2', 'Olga Filipova', 'Vue.js is one of the latest new frameworks to have piqued the interest of web developers due to its reactivity, reusable components, and ease of use.\r\n\r\nThis book shows developers how to leverage its features to build high-performing, reactive web interfaces with Vue.js. From the initial structuring to full deployment, this book provides step-by-step guidance to developing an interactive web interface from scratch with Vue.js.\r\n\r\nYou will start by building a simple application in Vue.js which will let you observe its features in action. Delving into more complex concepts, you will learn about reactive data binding, reusable components, plugins, filters, and state management with Vuex. This book will also teach you how to bring reactivity to an existing static application using Vue.js. By the time you finish this book you will have built, tested, and deployed a complete reactive application in Vue.js from scratch.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-learning-vue-js-2.jpg', 'http://file.nhasachmienphi.com/pdf/nhasachmienphi-learning-vue-js-2.pdf', '2025-05-07 21:35:53'),
(18, 4, 'Thuật Giải Mộng Của Người Xưa', 'Ngô Nguyên Tông', 'Người xưa cho rằng, mộng là sự liên lạc giữa con người và thần linh, là điềm báo cát hung hay phúc họa nên thuật giải mộng rất thịnh hành và trở thành một loại hình và trở thành một loại hình văn hóa đặc thù.\r\n\r\nDựa vào các sách cổ nói về thuật giải mộng, cuốn sách diễn giải các giấc mơ thường gặp của con người và đưa ra những lý giải sâu sắc, bất ngờ và thú vị về những sự việc tốt xấu có thể sắp xảy ra. Đặc biệt, sách còn cung cấp những phương pháp hợp lý giúp bạn tránh gặp ác mộng và có được những giấc mơ đẹp nhằm mang đến vận may, niềm tin vui, kể cả nhân duyên tốt cho tương lai.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-thuat-giai-mong-cua-nguoi-xua.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-thuat-giai-mong-cua-nguoi-xua.pdf', '2025-05-07 21:42:39'),
(19, 4, 'Sống Cùng Giấc Mơ', 'Thanh Châu - Quỳnh Trung', 'Sống Cùng Giấc Mơ là sách tham khảo khoa học huyền bí, một cuốn từ điển giải mã những biểu tượng xuất hiện trong giấc mơ của con người.\r\n\r\n“Thời tiết: Mơ về thời tiết, tiên báo những chiều hướng bấp bênh trong thời vận. Đương lúc bạn lên như diều gặp gió, thì bỗng dưng phải đối mặt với những nguy cơ thất bại.\r\n\r\nNgỡ bạn đang đọc bản báo cáo của cơ quan khí tượng thủy văn, ngụ ý bạn sẽ thay đổi nơi ở sau khi đã suy tính nát nước, nhưng thay đổi ấy lại hóa ra có ích.”', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-song-cung-giac-mo.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-song-cung-giac-mo.pdf', '2025-05-07 21:42:39'),
(20, 4, 'Phong Thủy Toàn Thư', 'Đổng Dị Lâm', 'Lịch sử văn minh nhân loại là một bộ lịch sử của sự không ngừng mưu cầu, sinh tồn và phát triển. Toàn bộ lí luận phong thuỷ cổ đại cũng là vì cứu sinh, vì sự trường thọ của sinh mệnh, vì sự thịnh vượng của con cháu, vì một cuộc sống phú quý.\r\n\r\nCũng giống như các văn hoá cổ khác của Trung Quốc, phong thuỷ có một sức thẩm thấu hết sức mạnh mẽ. Hiện nay, Thuật Phong Thủy đã không còn là một lĩnh vực độc quyền của người Trung Quốc nữa. Có thể nhận thấy phong trào nghiên cứu và ứng dụng học thuyết Phong Thuỷ vào cuộc sống thời gian gần đây đã phát triển rộng rãi, không chỉ ở các nước chịu ảnh hưởng của nền văn hoá Trung Hoa từ xa xưa như Nhật Bản, Hàn Quốc… mà còn lan đến tận các nước Âu – Mỹ và được nhiều người ủng hộ quan tâm. \r\n\r\nVới mục đích đáp ứng nhu cầu nghiên cứu, tham khảo của đông đảo bạn đọc, bằng sự liên hệ giữa học thuyết của cổ nhân với những vận dụng thực tế có tính khoa học nhằm mang lại hiệu quả tích cực trong cuộc sống ngày nay, cuốn sách “Phong Thủy toàn thư” với cách trình bày dễ hiểu, rõ ràng sẽ dẫn dắt độc giả nghiên cứu một cách có hệ thống về bộ môn độc đáo này của người Trung Quốc xưa… ', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-phong-thuy-toan-thu-2.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-phong-thuy-toan-thu-2.pdf', '2025-05-07 21:42:39'),
(21, 4, 'Tiếng Nói Của Bàn Tay', 'Nguyễn Đình Phúc', 'Bàn tay có nhiều đường nét, dấu, vết, màu sắc, hình thù và đặc điểm với những tên gọi khác nhau. Mỗi chi tiết ở trong lòng bàn tay hoặc trên mu bàn tay đều có tên gọi riêng đồng thời cũng mang ý nghĩa riêng. Mỗi ngón tay, mỗi đốt của từng ngón tay lại chứa đựng một nội dung khác nhau. Ở tùy từng loại bàn tay và loại người tốt xấu khác nhau mà người xem bàn tay – với kinh nghiệm của bản thân – sẽ phán đoán và giải đáp khác nhau. \r\n\r\nNghiên cứu tỉ mỉ khoa xem bàn tay, ta có thể biết ngay những ưu, khuyết điểm cơ bản của một người từ lúc mới gặp họ, bằng cách chỉ nhìn đôi bàn tay của họ đang hoạt động mà chưa cần tới việc cầm bàn tay của họ để nghiên cứu tỉ mỉ: như vậy rất có lợi cho mọi quan hệ giao tiếp hàng ngày. Đọc kỹ, nghiên cứu kỹ, cuốn sách này sẽ chứng minh rõ điều đó. \r\n\r\nBàn tay có tiếng nói riêng của nó. Nghiên cứu khoa xem bàn tay giúp bạn có thể hiểu, biết và khám phá ra chính mình. Hiểu biết và khám phá ra cái tốt, cái xấu của người khác, phát hiện bệnh tật sẽ xảy ra, báo trước cho người được xem biết phần nào tương lai của họ…\r\n\r\nNhững điều giới thiệu trong sách này được đúc kết qua kinh nghiệm thực tế từ bao đời nay, tuy còn nhiều khiếm khuyết nhưng hy vọng sẽ đem lại cho bạn nhiều điều thú vị.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tieng-noi-cua-ban-tay.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-tieng-noi-cua-ban-tay.pdf', '2025-05-07 21:42:39'),
(22, 4, 'Thiên Thời, Địa Lợi, Nhân Hòa', 'Bạch Huyết', 'Phương thuật Trung Quốc bắt nguồn từ tầng thứ cao nhất của triết học cổ đại, song nó lại diễn ra dưới hình thức tín ngưỡng dân gian phổ biến nhất. Hàng mấy ngàn năm nay, những phương thuật này được vận dụng vào các lĩnh vực chính trị, quân sự, khoa học kỹ thuật, văn nghệ, v.v… chứa đầy màu sắc thần bí, huyễn hoặc mà từ trước đến nay đã tạo nên những ảnh hưởng cực kì quan trọng đối với sinh hoạt xã hội, sự hình thành trạng thái tâm linh văn hóa của người Trung Quốc.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-thien-thoi-dia-loi-nhan-hoa.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-thien-thoi-dia-loi-nhan-hoa.pdf', '2025-05-07 21:42:39'),
(23, 5, 'Chăm sóc răng miệng toàn diện', 'Nadine Artemis', 'Chăm sóc răng miệng toàn diện.\r\nTrong cuốn sách này, tôi sẽ hướng dẫn cho bạn từng bước một trong ách chăm sóc răng miệng mới, loại bỏ những thói quen cũ không hiệu quả, để bạn có thể xây dựng được phần răng, nướu, nước bọt và cả men răng khỏe mạnh.\r\nTôi sẽ đưa vào một hành trình khám phá về răng miệng, để dần làm quen về mối quan hệ phức tạp giữa những yếu tố bên trong va 2be6n ngoài ảnh hưởng đến sức khỏe răng miệng. Bạn sẽ hiểu thế nào là hệ sinh thái răng miệng khỏe mạnh và học cách chủ động loại bỏ sâu răng bằng các bước thực hành giúp nuôi dưỡng răng và phục vụ toàn bộ cơ thể. Khi bạn áp dụng những kiến thức mới phát hiện này vào thực tế, bạn sẽ cảm thấy nước bọt của mình bao bọc bảo vệ răng nhiều hơn, chân nướu giữ răng vững chắc hơn và bạn sẽ thức dậy với hơi thở thơm tho, sẵn sàng để hôn một ngày mới. Ngay cả khi bạn vẫn đến nha sĩ, cuốn sách này sẽ giúp bạn đưa ra những quyết định sáng suốt cho sức khỏe lâu dài của răng miệng.', 'https://nhasachmienphi.com/wp-content/uploads/Ch%C4%83m-s%C3%B3c-r%C4%83ng-mi%E1%BB%87ng-to%C3%A0n-di%E1%BB%87n.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-cham-soc-rang-mieng-toan-dien1650247344.pdf', '2025-05-07 21:49:18'),
(24, 5, 'Chữa Lành Gương Mặt – Heal Your Face', 'Markus Rothkranz', 'Chữa Lành Gương Mặt – Heal Your Face\r\nTheo cách tự nhiên, không cần phẫu thuật\r\nBằng cách Chữa lành thân thể và cuộc sống\r\nTác giả: Markus Rothkranz\r\nTUYÊN BỐ TỪ CHỐI TRÁCH NHIỆM Y TẾ: Vui lòng chú ý rằng các thông tin và phát biểu trong sách này chưa được Cục quản lý thực phẩm và dược phẩm phê chuẩn. Các thông tin và ý kiến chứa đựng Bên trong không nhằm mục đích chẩn đoán, điều trị, chữa trị hay ngăn ngừa bệnh tật. Các phát biểu đều là ý kiến riêng và không tạo thành các chứng cứ hay đánh giá y khoa. Tài liệu này chỉ nhằm mục đích thông tin và không chủ định tư vấn y tế. Vì luôn có các rủi ro liên quan tới xuất bản tác phẩm thay thế, nên tác giả, nhà xuất bản và nhà phân phối không chịu trách nhiệm đối với các tác dụng bất lợi hoặc kết quả do việc sử\r\ndụng các gợi ý hay quy trình như mô tả sau đây.', 'https://nhasachmienphi.com/wp-content/uploads/Chua-lanh-guong-mat.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-chua-lanh-guong-mat-healing-your-face-markusrothkranz1646892996.pdf', '2025-05-07 21:49:18'),
(25, 5, 'Hệ Thống Làm Khỏe Lại và Sống Không Cần Thuốc', ' Paul Bragg – Người dịch Bùi Thanh Hằng', 'Hệ Thống Làm Khỏe Lại và Sống Không Cần Thuốc\r\nTrong quyển sách này bạn sẽ tìm được một cái nhìn mới thay đổi toàn bộ nếp nghĩ, nếp sống và cách rèn luyện sức khỏe, điều ảnh hưởng đến bạn và gia đình bạn.\r\nPaul Bragg – Tác phẩm chính và các bài giảng của ông\r\nĐiều tốt nhất mà một quyển sách có thể phục vụ bạn là không những nó thông báo cho bạn một chân lý mà còn bắt bạn phải suy nghĩ về chân lý đó.', 'https://nhasachmienphi.com/wp-content/uploads/he-thong-lam-khoe-lai-song-khong-can-thuoc-1.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-he-thong-lam-khoe-lai-song-khong-can-thuoc-11632148635.pdf', '2025-05-07 21:49:18'),
(26, 5, 'Sức khỏe trong tay bạn', 'Trần Bích Hà', 'Cuốn sách này bao gồm hai phần: Phần một là tập hợp những chia sẻ do tôi tham khảo được, đã “thử nghiệm” cho bản thân, bạn bè và gia đình để áp dụng các phương pháp tự nhiên trong chăm sóc, bảo vệ sức khỏe cũng như chữa các bệnh mãn tính. Phần hai là các kiến thức sưu tầm của tôi về các phương pháp chữa ung thư, được viết từ năm 2011 và 2012, vừa được tôi chỉnh sửa lại.', 'https://nhasachmienphi.com/wp-content/uploads/suc-khoe-trong-tay-ban.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-suc-khoe-trong-tay-ban-tran-bich-ha1623904606.pdf', '2025-05-07 21:49:18'),
(27, 6, '360 Động Từ Bất Quy Tắc Và 12 Thì Cơ Bản Trong Tiếng Anh', 'Nhiều tác giả', 'Cuốn sách này như một người bạn luôn nhắc nhở bạn dùng chính xác các dạng nguyên thể, quá khứ và phân từ của động từ. Mỗi động từ chúng tôi có đưa ra ví dụ để bạn có thể hiểu đươc cách dùng của động từ đó.để nhớ và dùng các động từ bất quy tắc này một cách tốt nhất các bạn lên học thuộc các ví dụ, từ đó các bạn sẽ nhớ được tình huống và vận dụng các động từ này một cách hiệu quả nhất', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-360-dong-tu-bat-quy-tac-va-12-thi-co-ban-trong-tieng-anh.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-360-dong-tu-bat-quy-tac-va-12-thi-co-ban-trong-tieng-anh.pdf', '2025-05-07 21:55:46'),
(28, 6, 'Ngữ Pháp Tiếng Anh Ôn Thi Toeic', 'Bộ Giáo Dục và Đào tạo', 'Đây là “Hệ thống ngữ pháp” chuẩn của Bộ giáo dục ban hàng trong loạt hệ thống kiến thức trọng tâm học ôn Toeic hiệu quả. Đúng như tên gọi, mục lớn này nhằm giúp người học biết, nắm bắt và hiểu một cách có hệ thống các chuyên đề ngữ pháp chính cần có để hoàn thành tốt bài thi Toeic mới với 2 phần chính là Nghe và Đọc.\r\n\r\nKhởi động vững chắc với việc làm quen, phân biệt và chia 12 thì cơ bản trong Tiếng Anh nhanh chóng, chính xác. Tiếp đó, chúng ta sẽ tự tin cùng học về đặc tính của các từ loại, câu, mệnh đề. Đặc biệt, người học sẽ hoàn tất khung ngữ pháp một cách chắc chắn khi được gợi nhắc về các trường hợp ngoại lệ dễ gặp trong bài thi liên quan tới ngữ pháp. Cụ thể, “hệ thống ngữ pháp” gồm 27 chuyên đề, mỗi chuyên đề gồm có lý thuyết trọng tâm, dễ hiểu và 20 câu hỏi trắc nghiệm ôn luyện hiệu quả từ dễ tới khó.\r\n\r\nCó thể nói, như một phần vai trò lên lộ trình ôn lại kiến thức ngữ pháp cho những ai mới bắt tay vào ôn thi Toeic cũng như không ít người đang và sẽ mong muốn có trong tay chứng chỉ giá trị này. Hãy luôn ghi nhớ sự nỗ lực của bạn sẽ quyết định bạn có thể nắm được hay không lượng kiến thức Tiếng Anh cần thiết trước khi tự tin đăng ký thi để chắc chắn có cho mình một điểm số Toeic cao. Chúc các bạn thành công và hãy bắt đầu với quyết tâm cao nhất nhé!', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-ngu-phap-tieng-anh-on-thi-toeic.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-ngu-phap-tieng-anh-on-thi-toeic.pdf', '2025-05-07 21:55:46'),
(29, 6, 'Ngữ Pháp Tiếng Hàn Cơ Bản', 'Lê Huy Khoa', 'NGỮ PHÁP CƠ BẢN TIẾNG HÀN của tác giả Lê Huy Khoa, hệ thống một cách đầy đủ, chính xác và khoa học nhất các kiến thức cơ bản về ngữ pháp tiếng Hàn như danh từ, động từ, tính từ…\r\n\r\nSách được phân chia theo chủ đề: mỗi chương đề cập đến một hình thức ngữ pháp cơ bản với các đề mục cụ thể như trợ từ, đại danh từ, động từ và tính từ bất quy tắc, thể chủ động…\r\n\r\nMỗi chủ đề ngữ pháp ngoài những trình bày về cấu trúc kèm ví dụ minh họa còn có những lưu ý về cách sử dụng và những bài thực hành luyện tập nhằm nâng cao kiến thức của người học.\r\n\r\nNgoài ra phần phiên âm trong quyển sách này được sử dụng phiên âm tiếng Việt thay cho phiên âm La tinh để bạn đọc dễ sử dụng hơn.\r\n\r\nThêm một ưu điểm nữa của cuốn sách là sự trình bày rõ ràng, dễ học, dễ tra cứu, các câu và từ tiếng Hàn được dịch và chuyển âm sang tiếng Việt dễ hiểu và dễ ứng dụng. Hy vọng cuốn sách này sẽ giúp ích cho tất cả các bạn đang học, nghiên cứu và cần sử dụng ngôn ngữ tiếng Hàn, giúp cho hai dân tộc Việt – Hàn ngày càng gắn bó và hiểu nhau hơn nữa.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-ngu-phap-tieng-han-co-ban.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-ngu-phap-tieng-han-co-ban.pdf', '2025-05-07 21:55:46'),
(30, 6, '301 Câu Đàm Thoại Tiếng Hoa', 'Nhiều tác giả', 'Giáo trình 301 Câu Đàm Thoại Tiếng Hoa được xuất bản lần đầu tiên vào năm 1990. Năm 1998, sách được chỉnh sửa, tái bản và được xếp vào hệ thống giáo trình tiếng Trung Quốc dành cho người ngước ngoài (tủ sách tinh hoa) của trường Đại học ngôn ngữ Bắc Kinh.\r\n\r\nGiáo trình 301 Câu Đàm Thoại Tiếng Hoa với các bảng chú thích bằng tiếng Anh, Pháp, Hàn, Nhật đã được tái bản nhiều lần và được độc giả khắp mọi nơi đón nhận nồng nhiệt, số lượng tiêu thụ lên đến hơn ba trăm ngàn bộ.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-301-cau-dam-thoai-tieng-hoa.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-301-cau-dam-thoai-tieng-hoa.pdf', '2025-05-07 21:55:46'),
(31, 6, 'Từ Điển Lào – Việt', 'Trần Kim Lân', 'Từ điển là một công cụ không thể thiếu cho các bạn trong quá trình học tiếng Lào.\r\n\r\nNhằm hỗ trợ các bạn trong việc tra cứu từ mới tiếng Lào, chúng tôi có sưu tầm và xin gửi tới các bạn Ebook từ điển Lào – Việt mới nhất của BS. Trần Kim Lân.\r\n\r\nCuốn từ Điển Lào Việt này bao gồm 50.000 từ và 25.000 ví dụ về ngữ cảnh, phần mở đầu của từ điển ngoài giới thiệu tác giả đã dành ra hẳn một chương mục giới thiệu cách đọc, phát âm tiếng Lào cũng như cách ghép âm tiết và chữ cái của Chữ Lào một cách cơ bản nhưng đầy đủ, chia ra 31 phân mục theo bảng chữ cái của Lào. Từ điển gồm hơn 1079 trang', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tu-dien-lao-viet.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-tu-dien-lao-viet.pdf', '2025-05-07 21:55:46'),
(32, 6, 'Từ Điển Pháp – Việt', 'Hồ Hải Thụy - Chu Khắc Thuật', 'Nhằm giúp bạn đọc biết nhiều hơn về một dân tộc đã giành được độc lập và tự do qua nhiều cuộc đấu tranh gian khổ cuốn Từ Điển Pháp Việt này đã được cho biên soạn.\r\n\r\nCuốn Từ điển Pháp – Việt này, do hai soạn giả Hồ Hải Thụy và Chu Khắc Thuật với sự cộng tác của một số giáo sư, phó giáo sư, tiến sĩ, phó tiến sĩ, chuyên gia thuộc các Viện nghiên cứu chuyên ngành biên soạn trong nhiều năm. Được biên soạn trên cơ sở những từ điển và tư liệu mới nhất của Việt Nam và Pháp.\r\n\r\nThu thập khối lượng từ lớn – 96.000 mục từ, thuộc nhiều chuyên ngành và phong cách khác nhau, phản ánh được từ vựng của tiếng Pháp hiện đại.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tu-dien-phap-viet.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-tu-dien-phap-viet.pdf', '2025-05-07 21:55:46'),
(33, 7, 'Tử huyệt cảm xúc', 'Roy Garn', '15 chương của cuốn sách là 15 chiếc chìa khóa đưa chúng ta giải mã sự bí ẩn của cảm xúc và sức mạnh của lời nói trong việc chi phối cảm xúc của con người như thế nào. Chẳng hạn hư vầy nhé, khi muốn ai đó làm gì cho bạn, thay vì bảo họ làm điều đó, bạn hãy hỏi ý họ về điều đó. Ví dụ, thay vì: “Đưa tôi cây bút đó!” thì sử dụng lời nói ít gây khó chịu hơn như: “Tôi dùng cây bút của bạn được không?”. Đây chỉ là một ví dụ nhỏ trong hàng trăm dẫn chứng được trình bày. Các hành động của con người với hành vi và lời nói của nhau chung quy đều là những phản ứng của cảm xúc. Chỉ là chúng ta có điều khiển được nó theo lý trí hay không.\r\n\r\nĐọc Tử huyệt cảm xúc, để có một chiếc chìa khóa mở ra con đường hiểu hơn về bản thân, về tử huyệt cảm xúc của chính mình, để thành công hơn và để biết mình sống chứ không phải chỉ đang tồn tại.', 'https://nhasachmienphi.com/wp-content/uploads/tu-huyet-cam-xuc.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-tu-huyet-cam-xuc1633786845.pdf', '2025-05-07 22:02:57'),
(34, 7, 'Ngày xưa có một con bò', 'Camilo Cruz', 'Ngày xưa có một con bò là một cuốn sách đầy thú vị và năng lượng tích cực.\r\n\r\nĐây là một cuốn sách có thể tạo cảm hứng thật sự cho bất kỳ ai.\r\nKhởi đầu bằng một câu chuyện mang tính ngụ ngôn vừa ngắn gọn, vừa dễ hiểu, cuốn sách rút ra một bài học sâu sắc nhưng thật đơn giản cho những ai vẫn đang vật lộn tìm kiếm thành công ở đời: những lời bao biện, viện cớ, đổ lỗi chẳng bao giờ giúp được chúng ta thành công hơn; ngược lại, dám dũng cảm đối mặt với sự trì trệ của mình để sẵn sàng tìm ra giải pháp mới là con đường tiến lên bền vững.\r\n\r\nCon bò vốn tượng trưng cho mọi lý lẽ, mọi thói quen hay sự bào chữa vốn khiến cho chúng ta không thể sống một cuộc đời trọn vẹn. Câu chuyện được viết ra một cách tuyệt vời này chắc chắn sẽ khiến bạn xiêu lòng, và đòi hỏi bạn phải loại bỏ tất cả sự biện bạch, hay nói như Camilo Cruz: “Tiêu diệt những con bò của bạn đi”.\r\n\r\nTôi vẫn thường cho rằng kẻ thù chính của sự thành công không phải sự thất bại, như nhiều người vẫn nghĩ ma là sự tầm thường- là cái tư tưởng cho rằng “như thế là tốt rồi”.', 'https://nhasachmienphi.com/wp-content/uploads/ngay-xua-co-mot-con-bo.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-ngay-xua-co-mot-con-bo-camilo-cruz1633173535.pdf', '2025-05-07 22:02:57'),
(35, 7, 'Bí quyết tay trắng thành triệu phú', 'Adam Khoo', '– Bạn là người có một khao khát mạnh mẽ muốn tự mình tạo dựng một gia sản hàng triệu đô mà chưa biết bắt đầu từ đâu?\r\n– Bạn nhận ra rằng với cách bạn đã làm việc quần quật 5 năm, 10 năm, 20 năm, thậm chí 30 năm vừa qua chưa phải là phương pháp đúng đắn giúp bạn trở nên giàu có?\r\n– Bạn mơ ước có thể thoát khỏi sự chật vật về tài chính đeo bám bạn suốt cuộc sống vừa qua?\r\n– Bạn tự hỏi, lý do gì mà nhiều người đã trở nên giàu có và cực kỳ giàu có, còn bạn thì chưa?\r\n\r\nBạn sẽ tìm thấy tất cả các câu trả lời cho những điều thắc mắc đó trong quyển sách “Bí Quyết Tay Trắng Thành Triệu Phú – Làm thế nào để có thu nhập dồi dào và gia tài bạc tỷ từ số 0″.\r\n\r\nQuyển sách chứa đựng những bí quyết đã giúp tác giả Adam Khoo trở thành một trong những triệu phú trẻ tuổi nhất ở Singapore khi anh mới ở tuổi 26, điều đặc biệt là anh khởi nghiệp hoàn toàn từ bàn tay trắng, không hề được thừa kế tài sản và chưa bao giờ vay ngân hàng hay nhận tiền đầu tư từ bên ngoài.', 'https://nhasachmienphi.com/wp-content/uploads/bi-quyet-tay-trang-thanh-trieu-phu-nhasachmienphi.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-bi-quyet-tay-trang-thanh-trieu-phu1609590887.pdf', '2025-05-07 22:02:57'),
(36, 7, 'Sinh Ra Để Chạy', 'Christopher McDougall', 'Sống ngăn cách với thế giới qua hẻm núi Copper Canyon (Mexico), người thổ dân Tarahumara đã tôi rèn khả năng chạy bộ hàng trăm dặm không ngơi nghỉ mà không gặp chấn thương nào. Nhà báo kỳ cựu đồng thời cũng là người chạy bộ hay mắc chấn thương Christopher McDougall đã lên đường tìm bí mật của họ. Dọc hành trình, anh đưa người đọc từ phòng thí nghiệm ở Đại học Harvard xuống thung lũng nóng như nung, lên những đỉnh cao buốt giá xuyên miền Bắc Mỹ, quê hương của người chạy bộ siêu phàm đẩy cơ thể đến cực hạn và cuối cùng là cuộc so tài thế kỷ trong hẻm núi Copper Canyon giữa những vận động viên chạy bộ cự phách nhất nước Mỹ và tộc người chạy bộ. Câu chuyện khó tin của McDougall không chỉ cuốn hút trí não mà còn tiếp sinh lực cho cơ thể khi bạn nhận ra từ thẳm sâu bên trong, mình được SINH RA ĐỂ CHẠY.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-sinh-ra-de-chay.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-sinh-ra-de-chay.pdf', '2025-05-07 22:02:57'),
(37, 7, 'Mặc Kệ Thiên Hạ – Sống Như Người Nhật', 'Mari Tamagawa', '“Nếu bạn có thể thẳng thắn đối diện với bản thân và từ bỏ những thứ đang giam cầm bạn, bạn sẽ trở thành con người có chính kiến, biết quý trọng hơn và đặt những cảm xúc của bản thân lên trên mọi ánh nhìn của người khác.”\r\n\r\nMari Tamagawa khuyến khích mỗi người nên sống thật và ngưng phán xét bản thân. Thật lãng phí khi bạn để cuộc đời mình phải trải qua những tháng ngày khổ sở chỉ vì cái nhìn của người khác. Cũng đừng tin vào những “chiếc phao” cứu cánh mà bạn nhầm tưởng rằng sẽ giúp mình rũ bỏ được mọi lo lắng. Tổn thương tâm lí, áp lực hay nỗi sợ… của bản thân, chúng ta phải đối mặt với nó, chiến đấu và tự chữa trị cho chính mình.\r\n\r\nĐã đến lúc bạn cần phải nói lời tạm biệt với cuộc sống đầy âu lo đang rút cạn năng lượng của bạn. Cuộc đời của bạn không thuộc về ai khác, cuộc đời của bạn thuộc về bạn. Tháng 3 này hãy để “Mặc kệ thiên hạ, sống như người Nhật” giúp bạn bắt đầu cuộc đời mới, cuộc đời mang tên chính mình.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-mac-ke-thien-ha-song-nhu-nguoi-nhat.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-mac-ke-thien-ha-song-nhu-nguoi-nhat.pdf', '2025-05-07 22:02:57'),
(38, 7, 'Đời Ngắn Đừng Ngủ Dài', 'Robin Sharma', 'diễn ra theo cách của nó, không phải theo cách của ta. Hãy kiên nhẫn. Tin tưởng. Hãy giống như người thợ cắt đá, đều đặn từng nhịp, ngày qua ngày. Cuối cùng, một nhát cắt duy nhất sẽ phá vỡ tảng đá và lộ ra viên kim cương. Người tràn đầy nhiệt huyết và tận tâm với việc mình làm không bao giờ bị chối bỏ. Sự thật là thế.”\r\n\r\nBằng những lời chia sẻ thật ngắn gọn, dễ hiểu về những trải nghiệm và suy ngẫm trong đời, Robin Sharma tiếp tục phong cách viết của ông từ cuốn sách Điều vĩ đại đời thường để mang đến cho độc giả những bài viết như lời tâm sự, vừa chân thành vừa sâu sắc.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-doi-ngan-dung-ngu-dai.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-doi-ngan-dung-ngu-dai.pdf', '2025-05-07 22:02:57'),
(39, 8, 'Horrible Science – Vi Sinh Vật Vi Tính', 'Nick Arnold - Tony De Saulles', 'Cuốn sách thuộc bộ Horrible Science, được trình bày bằng giọng văn và các minh họa hài hước quen thuộc của hai tác giả Nick Arnold và Tony De Saulles giúp bạn dễ dàng làm quen với kiến thức cơ bản nhất liên quan đến các loại vi khuẩn vi sinh.\r\n\r\nNhững hình vẽ và lời giản dị dễ hiểu – như những đoạn truyện tranh  hấp dẫn giúp bạn dễ tưởng tượng ra thế giới của những “động vật” tí hon, cùng với tên tuổi những nhà bác học trong ngành, những kết luận khoa học và nhất là những lời khuyên thực tế, hữu hiệu vô giá, những cảnh báo quan trọng cho lối sống hiện đại … Và nhờ thế mà cuốn sách được nhiều giải thưởng về sách khoa học dành cho thiếu nhi và được đông đảo bạn đọc từ trẻ em đến người lớn tìm đọc.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-horrible-science-vi-sinh-vat-vi-tinh.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-horrible-science-vi-sinh-vat-vi-tinh.pdf', '2025-05-07 22:09:51'),
(40, 8, 'Horrible Science – Vật Lý, Câu Chuyện Của Những Lực Bí Hiểm', 'Phil Gates, Tony De Saulles', 'Lực có thể mang lại những hậu quả nghiêm trọng. Ví dụ như khi bạn bị kẹt ngón tay vào khe cửa của lớp học, nguyên nhân không phải do ai đó khép cửa quá nhanh, mà thủ phạm ở đây là lực; lực còn có thể làm bạn bị té hay gây ra vụ nổ trong vũ trụ…\r\n\r\nQua những câu chuyện và tranh vẽ hài hước của cuốn sách này, bạn có thể đọc được rất nhiều điều thú vị về lực, về cách mà nó quyết định cuộc sống chúng ta… Tất cả đều có thật, rất có thể các loại lực rồi cũng sẽ tạo nên một lực hấp dẫn không thể cưỡng nổi đối với bạn', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-horrible-science-vat-ly-cau-chuyen-cua-nhung-luc-bi-hiem.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-horrible-science-vat-ly-cau-chuyen-cua-nhung-luc-bi-hiem.pdf', '2025-05-07 22:09:51'),
(41, 8, 'Horrible Science – Hóa Học – Một Vụ Nổ Ầm Vang', 'Nick Arnold - Tony De Saulles', 'Hóa học chỉ là thứ bốc mùi nồng nặc? Nghe đến từ này là bạn thấy hiện lên trong đầu mình toàn những phân tử rối rắm và những lý thuyết khô cằn? Nếu thế thì rõ là bạn chưa biết đến những khía cạnh thật sự thu hút đến điên khùng của môn Hóa. Ví dụ: Những thí nghiệm thất bại đến mức không thể thất bại hơn, Những quả bom hôi thối nhất thế giới, Hoặc phương pháp đơn giản nhất để làm ra vàng\r\n\r\nNhững bức tranh minh họa độc đáo, những chùm câu đố đặc sắc, những thí nghiệm ma quái và những câu chuyện ngoài sức tưởng tượng sẽ chỉ cho các bạn thấy tất cả những bộ mặt thật sự thú vị của Hóa học. Một số thứ khó tin đến mức cả các thầy giáo của bạn cũng sẽ phải sững sờ? Lúc đó thì Hóa học sẽ trở thành một VỤ NỔ ẦM VANG.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-horrible-science-hoa-hoc-mot-vu-no-am-vang.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-horrible-science-hoa-hoc-mot-vu-no-am-vang.pdf', '2025-05-07 22:09:51'),
(42, 8, 'Horrible Science – Cơ Thể Chúng Ta – Một Thế Giới Kỳ Thú', 'Nick Arnold - Tony De Saulles', 'Lệnh truy nã: bộ áo toàn thân siêu co dãn, tấm nệm lót lùng bùng béo ngậy, cặp camera tự động, đôi chảo con đón sóng âm,… gì nữa chỉ… À, bộ dây điện chằng chịt nè, một cái bánh kem tươi vỏ xoăn tít nè, cái khung trắng kêu rắc rắc nè, túi axít luôn miệng gầm gào, hai tảng bánh xốp ơi là xốp, bộ ống dẫn dài ngoằn ngoèo, hai túi lọc hình hạt đậu đen, cái máy bơm 24/24,… và vài lít sirô dâu nữa chứ. Và, haha, ta sẽ là giáo sư Frankenstein thế hệ mới!\r\n\r\nVà còn nhiều điều lý thú khác nữa, hãy cùng cuốn sách này khám phá thế giới kỳ thú trong cơ thể chúng ta, bạn nhé.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-horrible-science-co-the-chung-ta-mot-the-gioi-ky-thu.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-horrible-science-co-the-chung-ta-mot-the-gioi-ky-thu.pdf', '2025-05-07 22:09:51'),
(43, 8, 'Horrible Geography – Đại Dương Khó Thương', 'Anita Ganeri', 'Địa lý với những tấm bản đồ rắc rối, những khối đá trơ trơ và những đồ thị vô hồn có làm bạn chán ngấy? Yên tâm đi, trong Đại dương khó thương không hề có những thứ đó, chỉ toàn những chuyện rùng mình như: Cười khành khạch! Khi xơi ngon chú cá độc “bốc trời”… và no cành hông; Sởn da gà! Vì những con tàu mất tăm mất tích trong Tam giác Quỷ; Run như cầy sấy! Trước những tên hải tặc hung tợn, những kẻ khủng bố biển cả thời nay.\r\nToát mồ hôi! Vì những điều nôn ruột về cá mập trắng khổng lồ Và nếu thế vẫn chưa đủ khó thương với bạn? Hãy theo chúng tôi xuống đáy biển, đào xới những chuyện trời ơi về ngày cuối cùng của con tàu Titanic, xem bạn có máu hàng hải để gia nhập Hải quân không. Toàn chuyện rùng mình! Địa lý chưa bao giờ rùng rợn đến thế!', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-horrible-geography-dai-duong-kho-thuong.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-horrible-geography-dai-duong-kho-thuong.pdf', '2025-05-07 22:09:51'),
(44, 8, 'Mật Thư', 'Trần Thời', 'Mật thư vốn dĩ đã quá quen thuộc với các bạn trẻ yêu thích các hoạt động đội, nhóm, trại kỹ năng, hướng đạo… và là một phần không thể thiếu trong các trò chơi lớn.\r\n\r\nMật thư do tác giả Trần Thời biên soạn sẽ giới thiệu đến bạn đọc 9 kiểu mật thư, từ những dạng đơn giản nhất như đọc ngược, đọc lái từ, bỏ đầu bỏ đuôi, đến dạng kí hiệu morse, dạng thay thế, đọc theo khóa, dạng tượng hình hay tọa độ… Sau mỗi phần giới thiệu, bạn sẽ nhanh chóng được thử sức cùng những mật thư nho nhỏ nữa đấy.\r\n\r\nĐặc biệt, tác giả còn giới thiệu những loại “mực vô hình” để tạo nên những bản “mật thư trắng” huyền bí cùng những cách hóa giải đơn giản đến bất ngờ.\r\n\r\nHãy cùng mở sách và động não bạn nhé', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-mat-thu.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-mat-thu.pdf', '2025-05-07 22:09:51'),
(45, 9, 'Để xây dựng doanh nghiệp hiệu quả', 'Michael E. Gerber', 'E-Myth: Để xây dựng doanh nghiệp hiệu quả sẽ trả lời câu hỏi đó. Nếu bạn đang sở hữu hoặc muốn sở hữu một doanh nghiệp nhỏ, thì đây là cuốn sách dành riêng cho bạn. Là một hiện tượng nổi bật trên thị trường sách, E-Myth: Để xây dựng doanh nghiệp hiệu quả đã xoá tan những ngộ nhận về doanh nghiệp. Sau nhiều năm tích luỹ kinh nghiệm trong vai trò cố vấn cho các doanh nghiệp nhỏ, Michael E. Gerber, tác giả cuốn sách, đã chỉ ra cách điều hành doanh nghiệp hiệu quả.', 'https://nhasachmienphi.com/wp-content/uploads/the-emyth-de-xay-dung-doanh-nghiep-hieu-qua.jpg', 'https://file.nhasachmienphi.com/nhasachmienphi-de-xay-dung-doanh-nghiep-hieu-qua-michael-e-gerber1633260561.pdf', '2025-05-07 22:23:38'),
(46, 9, 'Từ Tơ Lụa Đến Silicon', 'Jeffrey E. Garten', 'Sách nghiên cứu cuộc đời và sự nghiệp của 10 nhân vật có nhiều ảnh hưởng, tạo ra những bước ngoặt trong sự phát triển của lịch sử thế giới, bao gồm: Gengis Khan, hoàng tử Henry, Robert Clive, Mayer Amschel Rothschild, Cyrus Field, John D. Rockefeller, Jean Monnet, Magaret Thatcher, Andrew Grove, Đặng Tiểu Bình và bàn thêm về người giỏi nhất còn chưa xuất hiện.\r\n\r\nTuy nhiên, sách không chỉ nói về lịch sử mà bàn về những việc làm của họ xét ở góc độ người quản lý hiện đại, vì thế sách có tính ứng dụng cho hiện tại và tương lai.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tu-to-lua-den-silicon.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-tu-to-lua-den-silicon.pdf', '2025-05-07 22:23:38'),
(47, 9, 'Đánh Bại Phố Wall', 'Peter Lynch', 'Với 13 năm kinh nghiệm quản lý thành công quỹ đầu tư Fidelity Magellan và lựa chọn hàng nghìn cổ phiếu, Lynch đã đúc kết thành 21 nguyên tắc hài hước mà ông gọi là “Những nguyên tắc của Peter”.\r\n\r\nChìa khóa để đầu tư thành công, theo Lynch, là phải ghi nhớ rằng cổ phiếu không giống như tấm vé số; luôn có một công ty đằng sau mỗi cổ phiếu và một nguyên nhân lý giải cho cách thức vận hành của các công ty – và cổ phiếu của chúng. Lynch chỉ ra làm cách nào chúng ta có thể tìm hiểu tối đa về công ty mục tiêu và xây dựng một danh mục đầu tư sinh lợi dựa trên chính kinh nghiệm, hiểu biết và kết quả nghiên cứu của bản thân. Không có bất kỳ lý do nào cản trở một nhà đầu tư cá nhân tự trở thành chuyên gia, và cuốn sách này sẽ chỉ ra cách thực hiện điều đó.', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-danh-bai-pho-wall.jpg', 'https://file.nhasachmienphi.com/pdf/nhasachmienphi-danh-bai-pho-wall.pdf', '2025-05-07 22:23:38');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`, `created_at`, `image_url`) VALUES
(1, 'Triết học', '2025-05-03 18:01:40', 'https://bizweb.dktcdn.net/thumb/1024x1024/100/414/537/products/vn-11134201-7r98o-lzg2he7xndjhea-1725079685845.jpg?v=1725079691243'),
(2, 'Trinh thám - Hình sự', '2025-05-03 18:01:40', 'https://static.oreka.vn/wp-content/uploads/2024/05/29162510/tieu-thuyet-trinh-tham-hay.jpg'),
(3, 'Công nghệ thông tin', '2025-05-03 18:01:40', 'https://images.nxbbachkhoa.vn/Picture/2022/9/21/image-20220921150259753.jpg'),
(4, 'Tử vi - Phong thuỷ', '2025-05-03 18:01:40', 'https://cdn.tgdd.vn/Files/2021/07/05/1365895/tim-hieu-6-cuon-sach-phong-thuy-ban-nen-doc-202107051939227020.jpg'),
(5, 'Y học - Sức khoẻ', '2025-05-03 18:01:40', 'https://media.vietnamplus.vn/images/7255a701687d11cb8c6bbc58a6c807852dba82d8769f57d1999383ef003f1316f3456b26c0c42a59fc321e309769072265a7b040ab99426558de9616b9deec7a/sach_y_hoc_tac_gia_Viet.jpg'),
(6, 'Học ngoại ngữ', '2025-05-07 19:05:11', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lx9hhw0etfq1f8'),
(7, 'Tâm lý - Kỹ năng sống', '2025-05-07 19:05:11', 'https://bizbooks.vn/uploads/images/2024/thang-12/sach-giao-tiep-1.jpg'),
(8, 'Khoa học - Kỹ thuật', '2025-05-07 19:05:11', 'https://newshop.vn/public/uploads/news/10-cuon-sach-hay-nhat-ve-khoa-hoc-min-1.jpg'),
(9, 'Kinh tế - Quản lý', '2025-05-07 19:05:11', 'https://govigroup.com/wp-content/uploads/2025/02/kinh-te-hoc-hai-huoc.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `favorites`
--

CREATE TABLE `favorites` (
  `favorite_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `favorites`
--

INSERT INTO `favorites` (`favorite_id`, `user_id`, `book_id`, `created_at`) VALUES
(1, 1, 46, '2025-05-17 04:11:02'),
(3, 1, 37, '2025-05-17 04:11:33'),
(6, 1, 15, '2025-05-17 04:52:50');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ratings`
--

CREATE TABLE `ratings` (
  `rating_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ratings`
--

INSERT INTO `ratings` (`rating_id`, `user_id`, `book_id`, `rating`, `comment`, `created_at`) VALUES
(21, 3, 5, 4, 'Sách rất hay và hữu ích', '2025-05-07 22:54:40'),
(22, 4, 12, 5, 'Tuyệt vời!', '2025-05-07 22:55:08'),
(23, 5, 20, 3, 'Nội dung ổn, hơi dài', '2025-05-07 22:55:08'),
(24, 6, 7, 4, 'Giá trị cao', '2025-05-07 22:55:08'),
(25, 7, 35, 2, 'Không như mong đợi', '2025-05-07 22:55:08'),
(26, 8, 18, 5, 'Rất đáng đọc', '2025-05-07 22:55:08'),
(27, 9, 25, 4, 'Cốt truyện cuốn hút', '2025-05-07 22:55:08'),
(28, 10, 11, 3, 'Tạm ổn', '2025-05-07 22:55:08'),
(29, 11, 43, 4, 'Phù hợp với sinh viên', '2025-05-07 22:55:08'),
(30, 12, 30, 5, 'Quá hay!', '2025-05-07 22:55:08'),
(31, 3, 2, 3, 'Chưa rõ ràng', '2025-05-07 22:55:08'),
(32, 4, 9, 4, 'Thuyết phục', '2025-05-07 22:55:08'),
(33, 5, 14, 5, 'Ngắn gọn dễ hiểu', '2025-05-07 22:55:08'),
(34, 6, 21, 2, 'Không đúng mô tả', '2025-05-07 22:55:08'),
(35, 8, 38, 5, 'Gợi mở tư duy', '2025-05-07 22:55:08'),
(36, 9, 45, 3, 'Khá ổn', '2025-05-07 22:55:08'),
(37, 10, 6, 4, 'Tác giả viết tốt', '2025-05-07 22:55:08'),
(38, 11, 28, 2, 'Chưa sâu sắc', '2025-05-07 22:55:08'),
(39, 12, 15, 4, 'Khuyên đọc', '2025-05-07 22:55:08'),
(40, 7, 5, 4, 'Sách rất hay và hữu ích', '2025-05-07 22:57:25'),
(41, 10, 12, 5, 'Tuyệt vời!', '2025-05-07 22:57:25'),
(42, 4, 20, 3, 'Nội dung ổn, hơi dài', '2025-05-07 22:57:25'),
(43, 1, 44, 3, 'kha cuon khi doc!', '2025-05-16 01:12:04'),
(44, 1, 47, 5, 'Rat huu ich', '2025-05-16 01:14:21'),
(45, 1, 42, 2, 'binh thuong', '2025-05-16 01:45:17'),
(46, 1, 21, 4, 'cung duoc', '2025-05-16 23:53:32'),
(47, 1, 17, 4, 'Kha hay, giup hieu duoc nhieu thu', '2025-05-16 23:59:31'),
(48, 1, 45, 5, 'Cuon hut', '2025-05-17 00:01:59');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reading_progress`
--

CREATE TABLE `reading_progress` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `last_page` int(11) DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reading_progress`
--

INSERT INTO `reading_progress` (`id`, `user_id`, `book_id`, `last_page`, `updated_at`) VALUES
(26, 1, 46, 42, '2025-05-18 15:21:53'),
(29, 1, 12, 15, '2025-05-18 15:29:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reset_tokens`
--

CREATE TABLE `reset_tokens` (
  `reset_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `token` varchar(64) NOT NULL,
  `expires_at` datetime NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reset_tokens`
--

INSERT INTO `reset_tokens` (`reset_id`, `user_id`, `token`, `expires_at`, `created_at`) VALUES
(1, 1, '365a44f8e131ba7029828d4cec8bd7a755d6d0ab1f2a4caf0081b73473f7e84d', '2025-05-19 16:59:55', '2025-05-19 21:44:55'),
(2, 1, 'de3b06c6ff5e2d489a3e137f610abaaf2500a7acbdb22f4e7b31d229af96f087', '2025-05-19 17:02:56', '2025-05-19 21:47:56'),
(3, 1, '9c3789b7e63a081ed88db25fd9deda4ed5662488183c4015da7da8d094d67d57', '2025-05-19 17:10:10', '2025-05-19 21:55:10'),
(4, 1, 'a55dcdccb5ba470254011863ca900a48ee7db2ac0df9b9e4d173821579b23808', '2025-05-19 17:12:46', '2025-05-19 21:57:46'),
(5, 1, 'ef215ec14200517f4ae293a8c603f4bb7c610230b917889be64cbe3c0b7301de', '2025-05-19 17:18:04', '2025-05-19 22:03:04'),
(6, 1, '7411c9f9e78c08f67df6e7790fd9ad0c8b9a9eef0d2c61459bc590827ee4ad70', '2025-05-19 18:46:26', '2025-05-19 23:31:26'),
(7, 1, '92d3ed691c5cb3a267ede75fcb15817401f714f8865535b2c375d6ffdc148b9a', '2025-05-19 18:52:53', '2025-05-19 23:37:53'),
(8, 1, '1af6b3107c3616eafe7f99ae643c71e068b5e6df8da43a0efb027d7c990f0dde', '2025-05-19 18:55:44', '2025-05-19 23:40:44'),
(9, 1, '763af46ae5280bef288f4d4a160eecd3db22fc05613b104f0824dffbccee4a0a', '2025-05-19 18:57:27', '2025-05-19 23:42:27'),
(10, 1, '7ad4be4c28d06a873a2fd87578b77bca75f36860b3e96c4cf83a4d1f598746eb', '2025-05-19 19:01:17', '2025-05-19 23:46:17'),
(11, 1, 'd7d24705c06e8256355f029e5a331a27ef93e515c9961420b5434a6fa2eb5b67', '2025-05-19 19:06:17', '2025-05-19 23:51:17');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `avatar_url` varchar(255) DEFAULT 'uploads/avt_default.png',
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password_hash`, `avatar_url`, `created_at`, `updated_at`) VALUES
(1, 'Thao Nhu Pham', '22521055@gm.uit.edu.vn', '$2y$10$c1E.ZJJQK.ACnpHbHHGjSeRhueg5xMwe.2FSVhRrAxtdXqDgBAdJK', 'uploads/682a3eb581a30_temp_image.jpg', '2025-05-01 02:00:13', '2025-05-19 23:54:54'),
(3, 'nguyenvana', 'nguyenvana@example.com', 'hash1', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:32'),
(4, 'tranthib', 'tranthib@example.com', 'hash2', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:35'),
(5, 'lehoangc', 'lehoangc@example.com', 'hash3', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:37'),
(6, 'phamthid', 'phamthid@example.com', 'hash4', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:46'),
(7, 'doanquange', 'doanquange@example.com', 'hash5', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:44'),
(8, 'buithef', 'buithef@example.com', 'hash6', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:42'),
(9, 'dangthig', 'dangthig@example.com', 'hash7', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:50'),
(10, 'hoangminhh', 'hoangminhh@example.com', 'hash8', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:39'),
(11, 'vothii', 'vothii@example.com', 'hash9', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:48'),
(12, 'truongthanhj', 'truongthanhj@example.com', 'hash10', 'uploads/avt_default.png', '2025-05-07 22:33:53', '2025-05-19 02:00:52');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_tokens`
--

CREATE TABLE `user_tokens` (
  `token_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_tokens`
--

INSERT INTO `user_tokens` (`token_id`, `user_id`, `token`, `expires_at`, `created_at`) VALUES
(6, 1, '1e52da2d9c72bd1bd018643cfefc1eb3fd1476fcae29a1e4547d0f979f85261e', '2025-05-20 00:11:09', '2025-05-19 23:56:09');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Chỉ mục cho bảng `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`favorite_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Chỉ mục cho bảng `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Chỉ mục cho bảng `reading_progress`
--
ALTER TABLE `reading_progress`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`book_id`);

--
-- Chỉ mục cho bảng `reset_tokens`
--
ALTER TABLE `reset_tokens`
  ADD PRIMARY KEY (`reset_id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Chỉ mục cho bảng `user_tokens`
--
ALTER TABLE `user_tokens`
  ADD PRIMARY KEY (`token_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `favorites`
--
ALTER TABLE `favorites`
  MODIFY `favorite_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `ratings`
--
ALTER TABLE `ratings`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `reading_progress`
--
ALTER TABLE `reading_progress`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=206;

--
-- AUTO_INCREMENT cho bảng `reset_tokens`
--
ALTER TABLE `reset_tokens`
  MODIFY `reset_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `user_tokens`
--
ALTER TABLE `user_tokens`
  MODIFY `token_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Các ràng buộc cho bảng `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);

--
-- Các ràng buộc cho bảng `ratings`
--
ALTER TABLE `ratings`
  ADD CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);

--
-- Các ràng buộc cho bảng `reset_tokens`
--
ALTER TABLE `reset_tokens`
  ADD CONSTRAINT `reset_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `user_tokens`
--
ALTER TABLE `user_tokens`
  ADD CONSTRAINT `user_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
