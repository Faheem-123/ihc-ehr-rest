package com.ihc.ehr.model;

import java.util.List;

public class Bundle<T>  {

		private Integer	total;
		private List<T> lst;
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
		}
		public List<T> getLst() {
			return lst;
		}
		public void setLst(List<T> lst) {
			this.lst = lst;
		}		
}
