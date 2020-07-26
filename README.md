Aplicación Android para prueba técnica.

# Características del Proyecto

El proyecto en general usa las herramientas que se listan a continuación:

* 100% [Kotlin](https://kotlinlang.org/)
* Arquitectura moderna (Clean Architecture con Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) para gestionar operaciones de fragmentos
* Inyección de dependencia
* Material design

# Tecnología

El nivel mímino de API es [`21`](https://android-arsenal.com/api?level=21#l21) por lo que el 90% de los telefónos android en el mercado serán compatibles con la alpicación, se usan las siguientes librerías y herramientas del ecosistema de Android:

* [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - para realizar operaciones en segundo plano.
* [Koin](https://insert-koin.io/) - Como inyector de dependencias
* [Retrofit](https://square.github.io/retrofit/) - Para consumo de servicios http
* [Lottie](http://airbnb.io/lottie)
* [Glide](https://bumptech.github.io/glide/)
* [Jetpack](https://developer.android.com/jetpack)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

* Architecture
    * Clean Architecture (Presentation-UseCases-Domain-Data)
    * MVVM (En la capa de presentación)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)

* Herramientas de análisis estático
    * Android Lint
    * ktlin
