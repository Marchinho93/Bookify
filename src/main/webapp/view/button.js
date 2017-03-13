(function() {
  'use strict';

  angular.module('button', ['ngMaterial', 'ngMdIcons'])
    .controller('buttonCtrl', function() {
      this.isOpen1 = false;
      this.isOpen2 = false;
      this.icon = {"label": "Book",
          "icon": "book",
          "action": ""};
      this.menu = {
        "icons1": [{
          "label": "Add",
          "icon": "add",
          "action": ""
        }, {
          "label": "Edit",
          "icon": "settings",
          "action": ""
        }, {
          "label": "Delete",
          "icon": "content_cut",
          "action": ""
        }]
      };
    });
})();
