# Fluxo Caixa Android

Este projeto é um aplicativo Android desenvolvido em Kotlin para gerenciar o fluxo de caixa de uma empresa. O aplicativo possui funcionalidades como reconhecimento óptico de caracteres (OCR) on-device, suporte a temas dinâmicos e uma interface moderna utilizando Jetpack Compose e Material 3. A arquitetura do projeto segue os princípios da Clean Architecture, garantindo uma separação clara entre as camadas de apresentação, domínio e dados.

## Estrutura do Projeto

- **app**: Módulo principal do aplicativo, contendo a interface do usuário e a lógica de apresentação.
- **core**: Módulo com utilitários e extensões que podem ser utilizados em todo o projeto.
- **domain**: Módulo que contém as regras de negócio, modelos de dados e casos de uso.
- **data**: Módulo responsável pela implementação da fonte de dados, incluindo acesso a banco de dados local.
- **ocr**: Módulo dedicado ao processamento de OCR para reconhecimento de texto em imagens.
- **presentation**: Módulo que contém a lógica de apresentação e os componentes da interface do usuário.

## Funcionalidades

- **OCR On-Device**: O aplicativo permite a digitalização de recibos e documentos utilizando OCR para extrair informações relevantes.
- **Gerenciamento de Transações**: Os usuários podem adicionar, visualizar e gerenciar transações financeiras, categorizando-as como receitas ou despesas.
- **Cálculo de Taxas**: O aplicativo calcula automaticamente as taxas aplicáveis às transações, permitindo uma visão clara dos custos.
- **Temas Dinâmicos**: Suporte a temas claros e escuros, proporcionando uma experiência de usuário personalizada.

## Casos de Uso

- **Adicionar Transação**: Permite que os usuários registrem novas transações financeiras.
- **Obter Resumo do Fluxo de Caixa**: Gera um resumo das transações em um período específico.
- **Escanear Recibo**: Processa uma imagem de recibo para extrair informações financeiras.
- **Aplicar Taxa**: Aplica taxas a uma transação com base nas configurações do usuário.
- **Alternar entre Bruto e Líquido**: Permite que os usuários visualizem transações em valores brutos ou líquidos.

## Testes

O projeto inclui testes unitários para verificar a lógica de cálculo de taxas e a funcionalidade do OCR, além de testes de UI utilizando Compose Testing para garantir a qualidade da interface do usuário.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorias e correções.

## Licença

Este projeto está licenciado sob a MIT License. Veja o arquivo LICENSE para mais detalhes.