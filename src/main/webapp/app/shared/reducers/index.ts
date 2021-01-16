import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import parameter, {
  ParameterState
} from 'app/entities/parameter/parameter.reducer';
// prettier-ignore
import dossierPatient, {
  DossierPatientState
} from 'app/entities/dossier-patient/dossier-patient.reducer';
// prettier-ignore
import antecedent, {
  AntecedentState
} from 'app/entities/antecedent/antecedent.reducer';
// prettier-ignore
import diagnostic, {
  DiagnosticState
} from 'app/entities/diagnostic/diagnostic.reducer';
// prettier-ignore
import indicationHd, {
  IndicationHdState
} from 'app/entities/indication-hd/indication-hd.reducer';
// prettier-ignore
import examenClinique, {
  ExamenCliniqueState
} from 'app/entities/examen-clinique/examen-clinique.reducer';
// prettier-ignore
import examenBioligique, {
  ExamenBioligiqueState
} from 'app/entities/examen-bioligique/examen-bioligique.reducer';
// prettier-ignore
import prescription, {
  PrescriptionState
} from 'app/entities/prescription/prescription.reducer';
// prettier-ignore
import traitementPerdialyse, {
  TraitementPerdialyseState
} from 'app/entities/traitement-perdialyse/traitement-perdialyse.reducer';
// prettier-ignore
import surveillance, {
  SurveillanceState
} from 'app/entities/surveillance/surveillance.reducer';
// prettier-ignore
import mesurePerdialyse, {
  MesurePerdialyseState
} from 'app/entities/mesure-perdialyse/mesure-perdialyse.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly parameter: ParameterState;
  readonly dossierPatient: DossierPatientState;
  readonly antecedent: AntecedentState;
  readonly diagnostic: DiagnosticState;
  readonly indicationHd: IndicationHdState;
  readonly examenClinique: ExamenCliniqueState;
  readonly examenBioligique: ExamenBioligiqueState;
  readonly prescription: PrescriptionState;
  readonly traitementPerdialyse: TraitementPerdialyseState;
  readonly surveillance: SurveillanceState;
  readonly mesurePerdialyse: MesurePerdialyseState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  parameter,
  dossierPatient,
  antecedent,
  diagnostic,
  indicationHd,
  examenClinique,
  examenBioligique,
  prescription,
  traitementPerdialyse,
  surveillance,
  mesurePerdialyse,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
