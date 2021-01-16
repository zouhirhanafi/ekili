import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IMesurePerdialyse } from 'app/shared/model/mesure-perdialyse.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './mesure-perdialyse.reducer';

export interface IMesurePerdialyseDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MesurePerdialyseDeleteDialog = (props: IMesurePerdialyseDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/mesure-perdialyse');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.mesurePerdialyseEntity.id);
  };

  const { mesurePerdialyseEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="ekiliApp.mesurePerdialyse.delete.question">
        <Translate contentKey="ekiliApp.mesurePerdialyse.delete.question" interpolate={{ id: mesurePerdialyseEntity.id }}>
          Are you sure you want to delete this MesurePerdialyse?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-mesurePerdialyse" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ mesurePerdialyse }: IRootState) => ({
  mesurePerdialyseEntity: mesurePerdialyse.entity,
  updateSuccess: mesurePerdialyse.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MesurePerdialyseDeleteDialog);
