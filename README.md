# 🚀 AgileFlow – Multiplatform Task Management System

**AgileFlow** là một ứng dụng quản lý task theo mô hình **Agile đa nền tảng**, hỗ trợ các phương pháp như **Scrum**, **Kanban** và **Cá nhân hóa**. Hệ thống cho phép theo dõi tiến độ, cộng tác nhóm, báo cáo sprint và mở rộng nhiều tính năng nâng cao như thời gian thực, đa ngôn ngữ, và tích hợp AI.

---

## 🛠️ Công nghệ sử dụng

| Layer       | Công nghệ                        |
|-------------|----------------------------------|
| Backend     | Spring Boot (REST API, JWT, JPA) |
| Web UI      | React + Tailwind CSS             |
| Mobile App  | React Native (Expo)              |
| Desktop App | Electron + React                 |
| Realtime    | WebSocket / Firebase             |
| DevOps      | Docker, GitHub Actions, Firebase Hosting |

---

## 📁 Cấu trúc thư mục

agileflow-multiplatform-system/
├── backend/ ← Spring Boot API
├── web-frontend/ ← React Web UI
├── mobile-app/ ← React Native (Expo)
├── desktop-app/ ← Electron + React
├── shared/ ← API docs, schema, interface chung
└── README.md


---

## 🔑 Tính năng chính

- 🧑‍🤝‍🧑 Quản lý người dùng, nhóm, phân vai trò (Scrum Master, Developer...)
- 📁 Quản lý dự án theo nhiều mô hình (Scrum / Kanban / Cá nhân)
- ✅ Tạo và quản lý Task (trạng thái, deadline, người thực hiện, mức độ ưu tiên)
- 🧭 Tạo Sprint, theo dõi tiến độ với burndown chart
- 💬 Thảo luận theo từng task, hỗ trợ realtime (Firebase / Socket.IO)
- 📈 Dashboard báo cáo hiệu suất nhóm, phân tích tiến độ
- 🔄 Chuyển đổi mô hình Scrum ⇄ Kanban ⇄ Personal
- 📦 Export dữ liệu sang PDF, CSV

---

## ⚙️ Tính năng nâng cao

| Tính năng nâng cao                  | Công nghệ gợi ý               |
|------------------------------------|-------------------------------|
| 💬 Bình luận thời gian thực        | Firebase Realtime DB / Socket.IO |
| 🌐 Đa ngôn ngữ giao diện            | React-i18next / LinguiJS      |
| 🧠 AI gợi ý chia task & deadline    | GPT API, Rule-based engine    |
| 📅 Tích hợp Google Calendar         | Google Calendar API           |
| 📦 Xuất dữ liệu PDF/CSV            | PDFMake, SheetJS              |

---

## 📦 Kế hoạch phát triển

1. **MVP**
   - Spring Boot API
   - Web UI (React) với quản lý Kanban cơ bản
   - Auth, CRUD Project/Task

2. **Giai đoạn mở rộng**
   - React Native App
   - Electron Desktop App
   - Realtime Comments

3. **Giai đoạn nâng cao**
   - AI Assistant (tự động chia task, gợi ý thời hạn)
   - Báo cáo chi tiết, tích hợp lịch, chia sẻ công việc

---

## 🧑‍💻 Đóng góp

Đang trong quá trình phát triển. Mọi đóng góp đều được hoan nghênh!

---

## 📄 Giấy phép

MIT License.
