# DomaWear

DomaWear é um aplicativo Android projetado para smartwatches com **Wear OS**, voltado à acessibilidade de funcionários com deficiência visual em ambientes empresariais. Ele oferece leitura de instruções sobre localização de setores da empresa via **Text-to-Speech** (TTS) e alertas automáticos em caso de emergência com som e voz.

## 📱 Funcionalidades

- ✅ Leitura automatizada de instruções sobre locais dentro da empresa (cafeteria, enfermaria, recepção etc.).
- ✅ Descrição falada inicial quando o app é iniciado.
- ✅ Botão acessível para ouvir as instruções de localização.
- ✅ Alertas de emergência disparados aleatoriamente com som e mensagem falada.
- ✅ Design pensado para usuários com deficiência visual (alto contraste, acessibilidade ativada).

---

## 🚀 Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/doma-wear.git
   ```

2. Abra no Android Studio.

4. Compile e rode o app em:
   - Emulador Wear OS
   - Dispositivo físico Wear OS
   - Ou smartphone comum (para testes de TTS)

---

## 📂 Estrutura do Projeto

```
📁 doma-wear/
 ┣ 📁 app/
 ┃ ┣ 📄 MainActivity.kt       # Tela principal com leitura de texto e alertas
 ┃ ┣ 📄 activity_main.xml     # Interface do usuário com acessibilidade
 ┃ ┗ 📁 res/
 ┃   ┗ 📁 raw/
 ┃     ┗ 📄 alert.mp3         # Som de alerta de emergência
 ┃
 ┣ 📄 build.gradle.kts        # Configurações do build
```

---

## 🔈 Tecnologias Usadas

- **Kotlin** como linguagem principal
- **Android Jetpack / AppCompat**
- **TextToSpeech (TTS)** para leitura de instruções
- **MediaPlayer** para áudio de alerta
- **AlertDialog** para notificações de emergência

---

## ♿️ Acessibilidade

O app foi desenhado com foco em:
- Leitura automática de instruções
- Botões com `contentDescription`
- Contraste de cores adequado
- Compatível com leitores de tela (TalkBack)

---

## 📍 Exemplos de Instruções Lidas

- "A 10 passos de onde você está, encontra-se a cafeteria."
- "À sua esquerda, a cerca de 15 metros, está a recepção."
- "O banheiro está localizada no final do corredor à direita."

---

## 👥 Contribuidores

- **Matheus José R. de Moura** – Desenvolvedor Principal