import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/parameter">
      <Translate contentKey="global.menu.entities.parameter" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/dossier-patient">
      <Translate contentKey="global.menu.entities.dossierPatient" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/antecedent">
      <Translate contentKey="global.menu.entities.antecedent" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/diagnostic">
      <Translate contentKey="global.menu.entities.diagnostic" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/indication-hd">
      <Translate contentKey="global.menu.entities.indicationHd" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/examen-clinique">
      <Translate contentKey="global.menu.entities.examenClinique" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/examen-bioligique">
      <Translate contentKey="global.menu.entities.examenBioligique" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/prescription">
      <Translate contentKey="global.menu.entities.prescription" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/traitement-perdialyse">
      <Translate contentKey="global.menu.entities.traitementPerdialyse" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/surveillance">
      <Translate contentKey="global.menu.entities.surveillance" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/mesure-perdialyse">
      <Translate contentKey="global.menu.entities.mesurePerdialyse" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
