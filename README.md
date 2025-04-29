
# Wefit Test App - Android

Este é um projeto Android modular desenvolvido em **Kotlin** com foco em **Clean Architecture (data, di, domain, presentation)**, utilizando **Jetpack Compose**, **Hilt**, **Retrofit**, **Coroutines**. O app foi estruturado com separação por módulos e segue boas práticas de arquitetura escalável.

## 🔧 Tecnologias e Bibliotecas

- **Linguagem:** Kotlin
- **UI:** Jetpack Compose
- **Injeção de Dependência:** Hilt
- **Comunicação com API:** Retrofit + Gson Converter
- **Gerenciamento de Estados e Ciclo de Vida:** ViewModel + LiveData
- **Coroutines:** para operações assíncronas

## 🗂️ Estrutura de Módulos

- `app`: Módulo principal de inicialização do app
- `base`: Código base reutilizável
- `cart`: Funcionalidade de carrinho
- `common`: Utilitários e recursos compartilhados
- `home`: Funcionalidade de tela inicial
- `network`: Utilizada para configuração de Retrofit
- `profile`: Módulo em desenvolvimento.

## 📁 Estrutura de Pacotes por Funcionalidade (Ex: `home`)

```
com.iagomichel.wefittest.home
├── data
│   ├── entity
│   ├── remote
│   └── repository
├── di
├── domain
│   ├── entity
│   ├── repository
│   └── usecase
└── presentation
```

Essa estrutura segue o padrão **Clean Architecture MVVM**, separando responsabilidades entre camadas de dados, regras de negócio e interface.

## 🚀 Como Rodar

1. Clone o repositório
2. Abra no Android Studio (Gira em AGP 8.4.2, Kotlin 1.9.25)
3. Configure seu emulador ou dispositivo
4. Rode o projeto a partir do módulo `app`

## ✅ Requisitos

- Android Studio Hedgehog ou superior
- JDK 17
- API mínima: 24
- Compile SDK: 35


---


