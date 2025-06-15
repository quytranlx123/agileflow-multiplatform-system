# agileflow-multiplatform-system
Ứng dụng quản lý task theo mô hình agile đa nền tảng

Công nghệ: 
Backend: Spring Boot
WebUI: React-TailwindCSS
Mobile: React Native(Expo)
Desktop: Electron + React
Realtime: WebSocket/Firebase
Devops: Docker, Github Action, Firebase hosting


agileflow-multiplatform-system/
├── backend/              ← Spring Boot
├── web-frontend/         ← React (web)
├── mobile-app/           ← React Native
├── desktop-app/          ← Electron + React
├── shared/               ← API docs, interface chung
└── README.md

| Mức nâng cao                       | Gợi ý                      |
| ---------------------------------- | -------------------------- |
| 💬 Realtime comment                | Socket.io / Firebase       |
| 🌐 Đa ngôn ngữ                     | i18n cho frontend          |
| 🧠 AI gợi ý task, tự chia deadline | GPT API, Rule-based engine |
| 📅 Lịch tích hợp (Google Calendar) | Google API                 |
| 📦 Export dữ liệu                  | PDF, CSV                   |
