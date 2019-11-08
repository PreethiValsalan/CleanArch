# CleanArch

This demo application is a Guide to Clean Architecture implementation in Android Applicationsusing kotlin.

<ul>
  <li>LiveData</li>
  <li>ViewModel</li>
  <li>DataBinding</li>
  <li>Paging Architecture</li>
  <li>Coroutines</li>
  <li>Coroutine Scope</li>
  <li>Retrofit</li>
  <li>Dagger</li>
  <li>Dagger-Android</li>
</ul>

The application has three libraries.
<ul>
  <li><b>Domain layer</b> - domain - Which represents the business logic of the application</li>
  <li><b>Data layer</b> - datawrapper- Which dispense the required data for the application to the domain layer from the data sources </li>
   <li><b>Presentation layer</b> - datawrapper- Which depends on the the domain and data layer to repesent the UI</li>
</ul>
