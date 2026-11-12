# Proffy Mobile 2026

Aplicativo Android (Kotlin) da disciplina de Programação para Dispositivos Móveis.

## Identificação

- **Nome completo:** Andressa Viana de Paula
- **Curso:** Sistemas de Informação
- **Turma:** 6º Período

## Fluxo "Esqueci minha senha"

A tela `ForgotPasswordActivity` (layout `activity_forgot_password.xml`) é aberta a partir do botão "Esqueci minha senha" da `LoginActivity`. A validação segue o padrão das telas de cadastro: um `addTextChangedListener` no campo de e-mail chama a função `setButtonState`, que habilita o botão (verde, texto "Enviar") apenas quando o e-mail é válido e o mantém desabilitado (cinza, texto "Entrar") caso contrário. O formato do e-mail é verificado por `ValidationUtils.isValidEmail`, utilitário que usa `Patterns.EMAIL_ADDRESS` e também é usado pela `RegisterStepTwoActivity`; quando o e-mail digitado é inválido, o erro aparece abaixo do campo via `TextInputLayout`, com o texto vindo do `strings.xml`. Ao tocar em "Enviar", a navegação é feita com uma `Intent` explícita para a `FinallyActivity`, enviando por `putExtra` o título, o subtítulo e o texto do botão da tela de conclusão, exatamente como a tela de cadastro já faz; o botão "Voltar ao login" da `FinallyActivity` abre a `LoginActivity` com as flags `FLAG_ACTIVITY_NEW_TASK` e `FLAG_ACTIVITY_CLEAR_TASK`, limpando a pilha de navegação.
