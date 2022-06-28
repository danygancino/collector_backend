// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {  
  production: false,
  //base_url: 'http://sagatechs.ddns.net:8104/avsi_asistencias/api/',
  //base_url: 'https://sagatechs.ddns.net:8467/avsi_asistencias/api/',
  //base_url: 'https://sistema.avsi.org:8443/avsi_asistencias/api/',
  base_url: 'http://localhost:8080/avsi_asistencias/api/',
  //base_url: 'https://track.trilogiconline.com:38444/avsi_asistencias/api/',
  //base_url: 'http://localhost:8080/avsi_asistencias/api/',
  app_code: 'ASISTENCIA_AVSI',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
