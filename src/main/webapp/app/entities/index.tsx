import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Parameter from './parameter';
import DossierPatient from './dossier-patient';
import Antecedent from './antecedent';
import Diagnostic from './diagnostic';
import IndicationHd from './indication-hd';
import ExamenClinique from './examen-clinique';
import ExamenBioligique from './examen-bioligique';
import Prescription from './prescription';
import TraitementPerdialyse from './traitement-perdialyse';
import Surveillance from './surveillance';
import MesurePerdialyse from './mesure-perdialyse';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}parameter`} component={Parameter} />
      <ErrorBoundaryRoute path={`${match.url}dossier-patient`} component={DossierPatient} />
      <ErrorBoundaryRoute path={`${match.url}antecedent`} component={Antecedent} />
      <ErrorBoundaryRoute path={`${match.url}diagnostic`} component={Diagnostic} />
      <ErrorBoundaryRoute path={`${match.url}indication-hd`} component={IndicationHd} />
      <ErrorBoundaryRoute path={`${match.url}examen-clinique`} component={ExamenClinique} />
      <ErrorBoundaryRoute path={`${match.url}examen-bioligique`} component={ExamenBioligique} />
      <ErrorBoundaryRoute path={`${match.url}prescription`} component={Prescription} />
      <ErrorBoundaryRoute path={`${match.url}traitement-perdialyse`} component={TraitementPerdialyse} />
      <ErrorBoundaryRoute path={`${match.url}surveillance`} component={Surveillance} />
      <ErrorBoundaryRoute path={`${match.url}mesure-perdialyse`} component={MesurePerdialyse} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
